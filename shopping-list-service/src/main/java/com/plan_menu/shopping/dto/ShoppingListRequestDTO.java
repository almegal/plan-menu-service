package com.plan_menu.shopping.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Объект передачи данных (DTO) для запроса на создание списка покупок.
 * Содержит информацию о названии списка покупок, описании и списке товаров.
 */
public record ShoppingListRequestDTO(
        @NotNull(message = "Name cannot be null")
        String name,
        String description,
        @Valid
        @NotEmpty(message = "Shopping list items cannot be empty")
        List<ShoppingListItemRequestDTO> items) {
}
