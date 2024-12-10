package org.java.java_backend_lab2_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;

public record CategoryCreateDto(
                                @NotBlank @Size(max = 255) String name,
                                @NotBlank String symbol,
                                @Size(max = 255) String description) {

    public CategoryEntity toCategoryEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name());
        categoryEntity.setSymbol(symbol());
        categoryEntity.setDescription(description());
        return categoryEntity;
    }
}
