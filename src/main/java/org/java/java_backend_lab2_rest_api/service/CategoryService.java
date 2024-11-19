package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
import org.java.java_backend_lab2_rest_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
}

public List<CategoryDto> allCategories() {
    return categoryRepository.findAll().stream()
            .map(CategoryDto::fromCategory)
            .toList();
}
}
