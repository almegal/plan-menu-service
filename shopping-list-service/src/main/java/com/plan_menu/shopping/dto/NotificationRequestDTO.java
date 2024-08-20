package com.plan_menu.shopping.dto;

/**
 * Объект передачи данных (DTO) для запроса на отправку уведомления.
 * Этот DTO содержит идентификатор пользователя, сообщение и тип уведомления.
 */
public record NotificationRequestDTO(
        Long userId,
        String message,
        String notificationType) {
}
