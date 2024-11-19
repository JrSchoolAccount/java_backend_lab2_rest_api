package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.CategoryDto.CategoryDto;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testAllCategories() {
        // Given
        CategoryEntity category1 = new CategoryEntity();
        category1.setName("Category1");
        category1.setSymbol("❤️");
        category1.setDescription("Description1");

        CategoryEntity category2 = new CategoryEntity();
        category2.setName("Category2");
        category2.setSymbol("💎");
        category2.setDescription("Description2");

        // Mock the repository method
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        // When
        List<CategoryDto> result = categoryService.allCategories();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());

        // Check the first DTO object
        CategoryDto dto1 = result.get(0);
        assertEquals("Category1", dto1.name());
        assertEquals("❤️", dto1.symbol());

        // Check the second DTO object
        CategoryDto dto2 = result.get(1);
        assertEquals("Category2", dto2.name());
        assertEquals("💎", dto2.symbol());
    }
}
