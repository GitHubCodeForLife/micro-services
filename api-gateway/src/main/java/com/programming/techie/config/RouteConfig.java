package com.programming.techie.config;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RouteConfig {

//    @Bean
//    public RouteLocator routeDefinitionRouteLocator(GatewayProperties properties,
//                                                    List<GatewayFilterFactory> gatewayFilters,
//                                                    List<RoutePredicateFactory> predicates,
//                                                    RouteDefinitionLocator routeDefinitionLocator,
//                                                    ConfigurationService configurationService) {
//        return new RouteDefinitionRouteLocator(routeDefinitionLocator, predicates, gatewayFilters, properties,
//                configurationService);
//    }
}
