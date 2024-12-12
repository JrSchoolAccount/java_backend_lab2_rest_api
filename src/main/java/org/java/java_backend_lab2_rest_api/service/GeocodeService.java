package org.java.java_backend_lab2_rest_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Value("${GEOCODE_API_KEY}")
    private String apiKey;


    public GeocodeService(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Retryable(maxAttempts = 3)
    public String getAddress(double latitude, double longitude) {
        String url = String.format("https://geocode.maps.co/reverse?lat=%s&lon=%s&api_key=%s",
                latitude, longitude, apiKey);
        log.info("Requesting URL: {}", url);
        try {
            String jsonRes = restClient.get().uri(url).retrieve().body(String.class);
            log.info("Raw API Response: {}", jsonRes);

            GeocodeResponseDto res = objectMapper.readValue(jsonRes, GeocodeResponseDto.class);
            log.info("Parsed Response received: {}", res);


            return res.display_name();
        } catch (RestClientException e) {
            throw new RestClientException("Failed to fetch display_name from Geocode API");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response");
        }
    }
}
