package com.plan_menu.shopping.dto;

import java.util.List;

/**
 * DTO для представления запроса на создание списка покупок.
 * Содержит информацию о пользователе и требуемых продуктах.
 */
public record ShoppingListRequestDTO(
        String userId,
        List<ShoppingListItemRequestDTO> items
) {}
