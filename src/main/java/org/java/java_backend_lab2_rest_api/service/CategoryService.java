package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.CategoryCreateDto;
import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.exception.DuplicateResourceException;
import org.java.java_backend_lab2_rest_api.exception.ResourceNotFoundException;
import org.java.java_backend_lab2_rest_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> allCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromCategory).toList();
    }

    public CategoryDto addCategory(CategoryCreateDto categoryCreateDto) {
        if (categoryRepository.existsByName(categoryCreateDto.name())) {
            throw new DuplicateResourceException("Category name must be unique.");
        }

        CategoryEntity category = categoryCreateDto.toCategoryEntity();

        category = categoryRepository.save(category);

        return new CategoryDto(category.getId(), category.getName(), category.getSymbol(), category.getDescription());
    }

    public CategoryDto getCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryDto(category.getId(), category.getName(), category.getSymbol(), category.getDescription()))
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found."));
    }
}
