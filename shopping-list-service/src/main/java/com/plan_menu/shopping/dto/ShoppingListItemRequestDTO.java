package com.plan_menu.shopping.dto;

import com.plan_menu.shopping.enums.MeasurementUnit;

/**
 * Объект передачи данных (DTO) для запроса на добавление товара в список покупок.
 * Этот DTO содержит идентификатор продукта, количество и единицу измерения.
 */
public record ShoppingListItemRequestDTO(
        Long productId,
        Integer quantity,
        MeasurementUnit unit) {
}
