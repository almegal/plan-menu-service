package com.plan_menu.shopping.dto;

import java.util.List;

/**
 * Объект передачи данных (DTO) для ответа с информацией о списке покупок.
 * Этот DTO содержит идентификатор списка покупок, название и список товаров.
 */
public record ShoppingListResponseDTO(
        Long id,
        String name,
        List<ShoppingListItemRequestDTO> items) {
}
