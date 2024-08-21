package com.plan_menu.shopping.dto;

import com.plan_menu.shopping.enums.MeasurementUnit;

/**
 * Объект передачи данных (DTO) для представления отдельного товара в списке покупок.
 * Содержит информацию о названии продукта, количестве, единице измерения и типе продукта.
 */
public record ShoppingListItemDTO(
        Long productId,
        Integer quantity,
        MeasurementUnit unit,
        Long shoppingListId) { // Добавлено поле shoppingListId
}
