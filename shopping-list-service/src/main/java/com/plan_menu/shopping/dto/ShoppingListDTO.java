package com.plan_menu.shopping.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Объект передачи данных (DTO) для передачи информации о списке покупок.
 * Этот DTO содержит идентификатор списка покупок, название, описание, дату создания,
 * статус, статус сборки, статус готовности, идентификатор пользователя и список товаров.
 */
public record ShoppingListDTO(
        Long id,
        String name,
        String description,
        LocalDateTime createdDate,
        String status,
        String collectionStatus,
        String readinessStatus,
        Long userId,
        List<ShoppingListItemDTO> items) {
}
