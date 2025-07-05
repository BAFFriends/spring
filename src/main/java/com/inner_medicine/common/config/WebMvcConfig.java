package com.inner_medicine.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    //CORS setting
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for Swagger UI endpoints
        registry.addMapping("/swagger-ui/**")
                .allowedOrigins("*") // You can specify your allowed origins here
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

        registry.addMapping("/swagger-resources/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
