package org.aston.service;

import org.aston.dto.ResponseCategoriesDTO;
import org.aston.entity.Category;
import org.aston.repository.CategoryRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepo repository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("Test getting all categories")
    void testGetAllCategories() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Завтрак"),
                new Category(2L, "Обед"),
                new Category(3L, "Ужин")
        );
        when(repository.findAll()).thenReturn(categories);

        ResponseCategoriesDTO response = categoryService.getAllCategories();

        assertEquals(3, response.categoryTitles().size());
        assertTrue(response.categoryTitles().contains("Завтрак"));
        assertTrue(response.categoryTitles().contains("Обед"));
        assertTrue(response.categoryTitles().contains("Ужин"));
    }
}
