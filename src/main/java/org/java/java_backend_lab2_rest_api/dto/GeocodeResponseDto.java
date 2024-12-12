package org.java.java_backend_lab2_rest_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeocodeResponseDto(@JsonProperty("display_name") String address) {
}
