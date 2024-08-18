package com.plan_menu.meal_plan_service.dto;

import java.util.List;

public record RecipeWithIngredientDto(
        Long id,
        String titleRecipe,
        List<IngredientDto> ingredientDtoList
) {
}
