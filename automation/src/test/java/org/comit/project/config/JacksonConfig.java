package org.comit.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}