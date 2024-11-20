package org.java.java_backend_lab2_rest_api.dto;

import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;

public record CategoryCreateDto(String name, String symbol, String description) {

    public CategoryEntity toCategoryEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name());
        categoryEntity.setSymbol(symbol());
        categoryEntity.setDescription(description());
        return categoryEntity;
    }
}
