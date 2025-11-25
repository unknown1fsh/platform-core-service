package com.sahip.platform.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Value("${external.sample-api.base-url:https://api.example.com}")
    private String sampleApiBaseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getSampleApiBaseUrl() {
        return sampleApiBaseUrl;
    }
}
