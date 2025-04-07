package com.programming.techie.web.reactive;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public class MyHttpHandler implements HttpHandler {
    @Override
    public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
        // Handle the request and set the response
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set("Content-Type", "text/plain");
        return response.writeWith(Mono.just(response.bufferFactory().wrap("Hello, World!112 ".getBytes())));
    }
}
