package com.plan_menu.meal_plan_service.dto;

public record IngredientDto(
        Long productId,
        String ingredientTitle,
        Double amount
) {
}
