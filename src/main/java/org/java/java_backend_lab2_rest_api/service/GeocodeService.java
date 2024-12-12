package org.java.java_backend_lab2_rest_api.service;

import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.GeocodeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class GeocodeService {

    private final RestClient restClient;

    @Value("${GEOCODE_API_KEY}")
    private String apiKey;


    public GeocodeService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retryable(maxAttempts = 3)
    public GeocodeResponseDto getAddress(double latitude, double longitude) {
        String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s",
                latitude, longitude, apiKey);
        log.info("Requesting URL: {}", url);
        try {
            GeocodeResponseDto response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(GeocodeResponseDto.class);

            log.info("Response received: {}", response.address());
            return response;
        } catch (RestClientException e) {
            log.error("Failed to fetch address from Geocode API", e);
            throw new RestClientException("Failed to fetch address from Geocode API", e);
        } catch (Exception e) {
            log.error("Unexpected error during API call or parsing", e);
            throw new RuntimeException("Unexpected error during API call or parsing", e);
        }
    }
}
