package org.java.java_backend_lab2_rest_api.dto;

import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;

public record CategoryDto(Integer id,
                          String name,
                          String symbol,
                          String description) {

    public static CategoryDto fromCategory(CategoryEntity category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getSymbol(),
                category.getDescription());
    }
}