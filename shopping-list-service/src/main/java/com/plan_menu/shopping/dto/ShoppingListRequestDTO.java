package com.plan_menu.shopping.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Объект передачи данных (DTO) для запроса на создание или обновление списка покупок.
 * Содержит информацию о списке покупок, такую как название, описание и список элементов.
 */
public record ShoppingListRequestDTO(
        @NotNull(message = "Name cannot be null")
        String name,

        String description,

        @Valid
        @NotEmpty(message = "Shopping list items cannot be empty")
        List<ShoppingListItemRequestDTO> items) {
}
