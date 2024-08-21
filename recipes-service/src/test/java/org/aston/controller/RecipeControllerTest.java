package org.aston.controller;

import org.aston.dto.RecipeWithIngredientDTO;
import org.aston.dto.ResponseCategoriesDTO;
import org.aston.dto.ResponseRecipeDTO;
import org.aston.service.CategoryService;
import org.aston.service.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Test getting recipes by ids")
    void testGetRecipesByIds() throws Exception {
        List<ResponseRecipeDTO> recipeDTOs = Arrays.asList(
                new ResponseRecipeDTO(1L, "Омлет", 11),
                new ResponseRecipeDTO(2L, "Каша", 12)
        );
        when(recipeService.getRecipesByIds(Arrays.asList(1L, 2L))).thenReturn(recipeDTOs);

        mockMvc.perform(get("/recipes")
                        .param("ids", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"recipeId\":1,\"recipeTitle\":\"Омлет\",\"timeForCook\":11}," +
                        "{\"recipeId\":2,\"recipeTitle\":\"Каша\",\"timeForCook\":12}]"));
    }

    @Test
    @DisplayName("Test getting recipe with ingredients by ids")
    void testGetRecipeWithIngredientsByIds() throws Exception {
        List<RecipeWithIngredientDTO> recipeDTOs = Arrays.asList(
                new RecipeWithIngredientDTO(1L, "Омлет", Collections.emptyList()),
                new RecipeWithIngredientDTO(2L, "Каша", Collections.emptyList())
        );
        when(recipeService.getRecipesWithIngredientsByIds(Arrays.asList(1L, 2L))).thenReturn(recipeDTOs);

        mockMvc.perform(get("/recipes/with/ingredient")
                        .param("ids", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"recipeTitle\":\"Омлет\",\"ingredientDTOs\":[]}," +
                        "{\"id\":2," + "\"recipeTitle\":\"Каша\",\"ingredientDTOs\":[]}]"));
    }

    @Test
    @DisplayName("Test getting recipes by category id")
    void testGetRecipesByCategoryId() throws Exception {
        List<ResponseRecipeDTO> recipeDTOs = Arrays.asList(
                new ResponseRecipeDTO(1L, "Омлет", 30),
                new ResponseRecipeDTO(2L, "Каша", 45)
        );
        when(recipeService.getRecipesByCategoryId(1L)).thenReturn(recipeDTOs);

        mockMvc.perform(get("/recipes/category/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"recipeId\":1,\"recipeTitle\":\"Омлет\",\"timeForCook\":30}," +
                        "{\"recipeId\":2,\"recipeTitle\":\"Каша\",\"timeForCook\":45}]"));
    }

    @Test
    @DisplayName("Test getting all categories")
    void testGetCategories() throws Exception {
        ResponseCategoriesDTO categoriesDTO = new ResponseCategoriesDTO(Collections.emptyList());
        when(categoryService.getAllCategories()).thenReturn(categoriesDTO);

        mockMvc.perform(get("/recipes/recipes/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"categoryTitles\":[]}"));
    }
}
