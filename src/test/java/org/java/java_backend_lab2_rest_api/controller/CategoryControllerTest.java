package org.java.java_backend_lab2_rest_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java.java_backend_lab2_rest_api.dto.CategoryCreateDto;
import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
import org.java.java_backend_lab2_rest_api.repository.CategoryRepository;
import org.java.java_backend_lab2_rest_api.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCategory_Success() throws Exception {
        CategoryDto mockResponse = new CategoryDto(1, "Category Name", "Symbol", "Description");
        when(categoryService.addCategory(any(CategoryCreateDto.class))).thenReturn(mockResponse);

        CategoryCreateDto requestBody = new CategoryCreateDto("Category Name", "Symbol", "Description");

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/categories/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Category Name"))
                .andExpect(jsonPath("$.symbol").value("Symbol"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void createCategory_InvalidRequest() throws Exception {
        CategoryDto mockResponse = new CategoryDto(1, "", "", "");
        when(categoryService.addCategory(any(CategoryCreateDto.class))).thenReturn(mockResponse);

        CategoryCreateDto requestBody = new CategoryCreateDto("", "", "");

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllCategories_Success() throws Exception {
        List<CategoryDto> mockResponse = List.of(
                new CategoryDto(1, "Category 1", "Symbol 1", "Description 1"),
                new CategoryDto(2, "Category 2", "Symbol 2", "Description 2")
        );

        when(categoryService.allCategories()).thenReturn(mockResponse);

        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Category 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Category 2"));
    }

    @Test
    void getCategoryById_Success() throws Exception {
        CategoryDto mockResponse = new CategoryDto(1, "Category 1", "Symbol 1", "Description 1");
        when(categoryService.getCategoryById(1)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Category 1"))
                .andExpect(jsonPath("$.symbol").value("Symbol 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

    @Test
    void getCategoryById_NotFound() throws Exception {
        when(categoryService.getCategoryById(999)).thenThrow(new RuntimeException("Category not found"));

        mockMvc.perform(get("/api/categories/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Category not found"));
    }
}
