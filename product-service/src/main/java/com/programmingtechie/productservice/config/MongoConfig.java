package com.programmingtechie.productservice.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public String dbUrl(MongoProperties mongoProperties) {
        return mongoProperties.getHost() + ":" + mongoProperties.getPort();
    }
}
