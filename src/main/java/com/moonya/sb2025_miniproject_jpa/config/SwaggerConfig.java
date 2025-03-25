package com.moonya.sb2025_miniproject_jpa.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi restApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/**")
                .group("REST API")
                .build();
    }
}
