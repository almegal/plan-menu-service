package com.plan_menu.shopping.dto;

/**
 * Объект передачи данных (DTO) для передачи информации об ошибках.
 * Этот DTO содержит идентификатор пользователя, код ошибки, сообщение об ошибке и дополнительные детали.
 */
public record ErrorDTO(
        Long userId,
        String errorCode,
        String message,
        String details) {
}
