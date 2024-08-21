package com.plan_menu.shopping.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Объект передачи данных (DTO) для ответа с информацией о списке покупок.
 * Содержит информацию о списке покупок, такую как идентификатор, название, описание,
 * дата создания, статус, статус сборки, статус готовности и список элементов.
 */
public record ShoppingListResponseDTO(
        Long id,
        String name,
        String description,
        LocalDateTime createdDate,
        String status,
        String collectionStatus,
        String readinessStatus,
        Long mealPlanId, // Добавлено поле mealPlanId
        List<ShoppingListItemDTO> items) {
}
