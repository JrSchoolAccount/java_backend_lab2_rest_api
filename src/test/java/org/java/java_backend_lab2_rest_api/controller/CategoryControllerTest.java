package org.java.java_backend_lab2_rest_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java.java_backend_lab2_rest_api.dto.CategoryCreateDto;
import org.java.java_backend_lab2_rest_api.dto.CategoryDto;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.repository.CategoryRepository;
import org.java.java_backend_lab2_rest_api.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
