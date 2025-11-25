package com.sahip.platform.core.client;

import com.sahip.platform.core.config.ClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ExternalApiClient {

    private final RestTemplate restTemplate;
    private final ClientConfig clientConfig;

    public String getSampleData() {
        String url = clientConfig.getSampleApiBaseUrl() + "/sample";
        return restTemplate.getForObject(url, String.class);
    }
}
