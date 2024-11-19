package org.java.java_backend_lab2_rest_api.dto;

import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;

public record CategoryDto(String name, String symbol) {

    public static CategoryDto fromCategory(CategoryEntity category) {
        return new CategoryDto(category.getName(), category.getSymbol());
    }
}