package com.plan_menu.shopping.dto;

import java.util.Map;

/**
 * DTO для приема данных от Планировщика Меню.
 * Содержит идентификатор пользователя и список покупок.
 */
public record MenuPlannerShoppingListRequestDTO(
        Long userId,
        Map<String, Double> shoppingList // Название продукта и его количество
) {
}
