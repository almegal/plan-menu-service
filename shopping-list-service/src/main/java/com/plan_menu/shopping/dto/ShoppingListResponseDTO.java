package com.plan_menu.shopping.dto;

import java.util.List;

/**
 * DTO для представления ответа от Shopping List Service после обработки запроса.
 * Содержит ID списка покупок, статус и фактически подобранные продукты.
 */
public record ShoppingListResponseDTO(
        Long shoppingListId,
        String userId,
        String status,
        List<ShoppingListItemResponseDTO> items
) {}
