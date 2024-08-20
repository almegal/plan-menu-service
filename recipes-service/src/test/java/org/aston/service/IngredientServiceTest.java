package org.aston.service;

import org.aston.dto.IngredientDTO;
import org.aston.entity.RecipeIngredientPK;
import org.aston.entity.RecipeIngredients;
import org.aston.mapper.IngredientMapper;
import org.aston.repository.IngredientRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientMapper mapper;
    @Mock
    private IngredientRepo ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    @DisplayName("Test getting ingredients by recipe id")
    void testGetIngredientsByRecipeId() {
        long recipeId = 1L;
        List<RecipeIngredients> ingredients = Arrays.asList(
                new RecipeIngredients(new RecipeIngredientPK(1L, 1L), 1.0, "Яйцо"),
                new RecipeIngredients(new RecipeIngredientPK(2L, 1L), 2.0, "Молоко")
        );
        List<IngredientDTO> ingredientDTOs = Arrays.asList(
                new IngredientDTO(1L, 1.0, "Яйцо"),
                new IngredientDTO(1L, 2.0, "Молоко")
        );
        when(ingredientRepository.findByIdRecipeId(recipeId)).thenReturn(ingredients);
        when(mapper.mapEntitiesToDtos(ingredients)).thenReturn(ingredientDTOs);

        List<IngredientDTO> result = ingredientService.getIngredientsByRecipeId(recipeId);

        assertEquals(ingredientDTOs, result);
    }
}
