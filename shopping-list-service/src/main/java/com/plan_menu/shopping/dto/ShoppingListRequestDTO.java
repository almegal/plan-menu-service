package com.plan_menu.shopping.dto;

import java.util.Map;

/**
 * Объект передачи данных (DTO) для запроса на создание или обновление списка покупок.
 * Этот DTO содержит идентификатор пользователя и карту продуктов с их количеством.
 */
public record ShoppingListRequestDTO(
        Long userId,
        Map<Long, Integer> shoppingList) {
}
