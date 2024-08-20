package org.aston.mapper;

import org.aston.dto.ResponseRecipeDTO;
import org.aston.entity.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeMapperTest {

    private final RecipeMapper recipeMapper = new RecipeMapper();

    @Test
    @DisplayName("Test mapping recipe entity to dto")
    void entityToDto_ShouldReturnCorrectDto() {
        Recipe recipe = Recipe.builder().recipeId(1L).recipeTitle("Омлет").timeForCook(12).build();
        ResponseRecipeDTO result = recipeMapper.entityToDto(recipe);

        assertEquals(1L, result.recipeId());
        assertEquals("Омлет", result.recipeTitle());
        assertEquals(12, result.timeForCook());
    }
}
