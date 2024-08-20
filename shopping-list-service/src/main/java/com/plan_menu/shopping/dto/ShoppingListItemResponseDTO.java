package com.plan_menu.shopping.dto;

/**
 * DTO для представления отдельного продукта в ответе от Shopping List Service.
 * Содержит информацию о фактически предоставленном продукте, его объеме и весе.
 */
public record ShoppingListItemResponseDTO(
        Long productId,
        String productName,
        double providedVolume,
        double providedWeight) {
}
