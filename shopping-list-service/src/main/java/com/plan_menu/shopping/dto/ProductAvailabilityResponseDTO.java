package com.plan_menu.shopping.dto;

/**
 * Объект передачи данных (DTO) для ответа с информацией о доступности продукта.
 * Этот DTO содержит идентификатор продукта, флаг доступности и предполагаемую дату пополнения запасов.
 */
public record ProductAvailabilityResponseDTO(
        Long productId,
        Boolean available,
        String estimatedRestockDate) {
}
