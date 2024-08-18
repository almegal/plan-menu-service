package com.plan_menu.meal_plan_service.dto;

import java.util.Map;

public record ShoppingListRequestDto(
        Long userId,
        Map<String, Double> shoppingList
) {
}
