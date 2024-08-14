package com.plan_menu.shopping.dto;

/**
 * DTO для представления отдельного продукта в запросе на создание списка покупок.
 * Содержит информацию о запрашиваемом продукте, его объеме и весе.
 */
public record ShoppingListItemRequestDTO(
        String productName,
        double requestedVolume,
        double requestedWeight
) {}
