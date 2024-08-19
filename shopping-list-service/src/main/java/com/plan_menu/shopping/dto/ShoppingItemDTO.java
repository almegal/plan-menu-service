package com.plan_menu.shopping.dto;

import com.plan_menu.shopping.enums.MeasurementUnit;

/**
 * Объект передачи данных (DTO) для представления отдельного товара в списке покупок.
 * Содержит информацию о названии продукта, количестве, единице измерения и типе продукта.
 */
public record ShoppingItemDTO(
        /**
         * Название продукта.
         */
        String productName,

        /**
         * Количество продукта.
         */
        int quantity,

        /**
         * Единица измерения продукта.
         */
        MeasurementUnit unit,

        /**
         * Тип продукта.
         */
        String productType) {
}
