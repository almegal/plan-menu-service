package com.plan_menu.shopping.dto;

/**
 * Объект передачи данных (DTO) для уведомлений.
 * Этот DTO содержит идентификатор пользователя и сообщение.
 */
public record NotificationDTO(
        Long userId,
        String message) {
}
