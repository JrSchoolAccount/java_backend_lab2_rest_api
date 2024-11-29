package org.java.java_backend_lab2_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LocationUpdateDto(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Pattern(regexp = "PUBLIC|PRIVATE", message = "Status must be either PUBLIC or PRIVATE") String status,
        @Size(max = 255) String description) {
}
