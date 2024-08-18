package com.plan_menu.meal_plan_service.feign;

import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service")
public interface OrderServiceClient {
    @PostMapping("/orders/")
    HttpStatus sendListOrder(ShoppingListRequestDto lists);
}
