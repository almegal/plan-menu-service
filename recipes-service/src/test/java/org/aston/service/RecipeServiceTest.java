package org.aston.service;

import org.aston.dto.IngredientDTO;
import org.aston.dto.RecipeWithIngredientDTO;
import org.aston.dto.ResponseRecipeDTO;
import org.aston.entity.Recipe;
import org.aston.mapper.RecipeMapper;
import org.aston.repository.RecipeRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepo recipeRepository;
    @Mock
    private RecipeMapper recipeMapper;
    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    @DisplayName("Test getting recipes by ids")
    void testGetRecipesByIds() {
        List<Long> recipeIds = Arrays.asList(1L, 2L);
        Recipe recipe1 = Recipe.builder().recipeId(1L).recipeTitle("Омлет").timeForCook(12).build();
        Recipe recipe2 = Recipe.builder().recipeId(2L).recipeTitle("Блины").timeForCook(13).build();

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe1));
        when(recipeRepository.findById(2L)).thenReturn(Optional.of(recipe2));
        when(recipeMapper.entityToDto(recipe1)).thenReturn(new ResponseRecipeDTO(1L, "Омлет", 12));
        when(recipeMapper.entityToDto(recipe2)).thenReturn(new ResponseRecipeDTO(2L, "Блины", 13));

        List<ResponseRecipeDTO> result = recipeService.getRecipesByIds(recipeIds);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).recipeId());
        assertEquals("Омлет", result.get(0).recipeTitle());
        assertEquals(2L, result.get(1).recipeId());
        assertEquals("Блины", result.get(1).recipeTitle());
    }

    @Test
    @DisplayName("Test getting recipes with ingredients by ids")
    void testGetRecipesWithIngredientsByIds() {
        List<Long> recipeIds = Arrays.asList(1L, 2L);
        List<Recipe> recipes = Arrays.asList(
                Recipe.builder().recipeId(1L).recipeTitle("Омлет").timeForCook(12).build(),
                Recipe.builder().recipeId(2L).recipeTitle("Блины").timeForCook(13).build()
        );
        List<IngredientDTO> ingredientDTOs1 = Arrays.asList(
                new IngredientDTO(1L, 2.0, "Яйцо"),
                new IngredientDTO(2L, 100.0, "Молоко")
        );
        List<IngredientDTO> ingredientDTOs2 = Arrays.asList(
                new IngredientDTO(1L, 4.0, "Яйцо"),
                new IngredientDTO(2L, 200.0, "Молоко")
        );
        List<RecipeWithIngredientDTO> expectedResult = Arrays.asList(
                new RecipeWithIngredientDTO(1L, "Омлет", ingredientDTOs1),
                new RecipeWithIngredientDTO(2L, "Блины", ingredientDTOs2)
        );
        when(recipeRepository.findAllById(recipeIds)).thenReturn(recipes);
        when(ingredientService.getIngredientsByRecipeId(1L)).thenReturn(ingredientDTOs1);
        when(ingredientService.getIngredientsByRecipeId(2L)).thenReturn(ingredientDTOs2);

        List<RecipeWithIngredientDTO> result = recipeService.getRecipesWithIngredientsByIds(recipeIds);

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test getting recipes by category id")
    void testGetRecipesByCategoryId() {
        long categoryId = 1L;
        List<Recipe> recipes = Arrays.asList(
                Recipe.builder().recipeId(1L).recipeTitle("Омлет").timeForCook(12).build(),
                Recipe.builder().recipeId(2L).recipeTitle("Блины").timeForCook(13).build(),
                Recipe.builder().recipeId(3L).recipeTitle("Квас").timeForCook(14).build()
        );
        List<ResponseRecipeDTO> expectedResult = Arrays.asList(
                new ResponseRecipeDTO(1L, "Омлет", 12),
                new ResponseRecipeDTO(2L, "Блины", 13),
                new ResponseRecipeDTO(3L, "Квас", 14)
        );
        when(recipeRepository.findByCategoryCategoryId(categoryId)).thenReturn(recipes);
        when(recipeMapper.entityToDto(any(Recipe.class))).thenAnswer(i -> {
            Recipe recipe = i.getArgument(0);
            return new ResponseRecipeDTO(recipe.getRecipeId(), recipe.getRecipeTitle(), recipe.getTimeForCook());
        });

        List<ResponseRecipeDTO> result = recipeService.getRecipesByCategoryId(categoryId);

        assertEquals(expectedResult, result);
    }
}
