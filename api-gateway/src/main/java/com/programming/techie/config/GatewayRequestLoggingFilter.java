package com.programming.techie.config;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;


@Component
public class GatewayRequestLoggingFilter implements GlobalFilter, Ordered {

    Logger log = LoggerFactory.getLogger(GatewayRequestLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getPath().toString();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        DataBufferFactory dataBufferFactory = response.bufferFactory();

        // log the request body
        ServerHttpRequest decoratedRequest = getDecoratedRequest(request);
        // log the response body
        ServerHttpResponseDecorator decoratedResponse = getDecoratedResponse(path, response, request, dataBufferFactory);
        return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build());
    }

    private ServerHttpResponseDecorator getDecoratedResponse(String path, ServerHttpResponse response, ServerHttpRequest request, DataBufferFactory dataBufferFactory) {
        return new ServerHttpResponseDecorator(response) {

            @Override
            public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {

                if (body instanceof Flux) {

                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;

                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {

                        DefaultDataBuffer joinedBuffers = new DefaultDataBufferFactory().join(dataBuffers);
                        byte[] content = new byte[joinedBuffers.readableByteCount()];
                        joinedBuffers.read(content);
                        String responseBody = new String(content, StandardCharsets.UTF_8);//MODIFY RESPONSE and Return the Modified response
                        log.info("requestId: {}, method: {}, url: {}, \nresponse body :{}", request.getId(), request.getMethod(), request.getURI(), responseBody);

                        return dataBufferFactory.wrap(responseBody.getBytes());
                    })).onErrorResume(err -> {

                        log.error("error while decorating Response: {}",err.getMessage());
                        return Mono.empty();
                    });

                }
                return super.writeWith(body);
            }
        };
    }

    private ServerHttpRequest getDecoratedRequest(ServerHttpRequest request) {

        return new ServerHttpRequestDecorator(request) {
            @Override
            public Flux<DataBuffer> getBody() {

                log.info("requestId: {}, method: {} , url: {}", request.getId(), request.getMethod(), request.getURI());
                return super.getBody().publishOn(Schedulers.boundedElastic()).doOnNext(dataBuffer -> {

                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                        Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                        String requestBody = byteArrayOutputStream.toString(StandardCharsets.UTF_8);
                        log.info("for requestId: {}, request body :{}", request.getId(), requestBody);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                });
            }
        };
    }

    @Override
    public int getOrder() {
        return -2;
    }
}