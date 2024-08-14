package com.plan_menu.shopping.dto;

import java.util.List;

public record ShoppingListDTO(Long userId, List<ShoppingItemDTO> items) {}

