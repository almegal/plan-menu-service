package org.aston.dto;

import java.util.List;

public record RecipeWithIngredientDTO(
        Long id,
        String recipeTitle,
        List<IngredientDTO> ingredientDTOs
) {}
