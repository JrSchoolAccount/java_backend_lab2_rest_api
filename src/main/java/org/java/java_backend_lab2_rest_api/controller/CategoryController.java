package org.java.java_backend_lab2_rest_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.CategoryCreateDto;
import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
import org.java.java_backend_lab2_rest_api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.allCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);

        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        CategoryDto newCategory = categoryService.addCategory(categoryCreateDto);

        URI location = URI.create("/api/categories/" + newCategory.id());

        return ResponseEntity.created(location).body(newCategory);
    }
}
