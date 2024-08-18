package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrderService {
    public HttpStatus makeOrder(List<ShoppingListRequestDto> shopList);
}
