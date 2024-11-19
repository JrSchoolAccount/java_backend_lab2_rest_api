package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
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
        CategoryEntity category1 = new CategoryEntity();
        category1.setName("Category1");
        category1.setSymbol("❤️");
        category1.setDescription("Description1");

        CategoryEntity category2 = new CategoryEntity();
        category2.setName("Category2");
        category2.setSymbol("💎");
        category2.setDescription("Description2");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<CategoryDto> result = categoryService.allCategories();

        assertNotNull(result);
        assertEquals(2, result.size());

        CategoryDto dto1 = result.get(0);
        assertEquals("Category1", dto1.name());
        assertEquals("❤️", dto1.symbol());

        CategoryDto dto2 = result.get(1);
        assertEquals("Category2", dto2.name());
        assertEquals("💎", dto2.symbol());
    }
}
