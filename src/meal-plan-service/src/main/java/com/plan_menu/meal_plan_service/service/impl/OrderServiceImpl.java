package com.plan_menu.meal_plan_service.service.impl;

import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import com.plan_menu.meal_plan_service.feign.OrderServiceClient;
import com.plan_menu.meal_plan_service.service.OrderService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderServiceClient orderServiceClient;

    public OrderServiceImpl(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public HttpStatus makeOrder(ShoppingListRequestDto shopList) throws FeignException{
        return orderServiceClient.sendListOrder(shopList);
    }
}
