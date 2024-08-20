package com.plan_menu.shopping.feign;

import com.plan_menu.shopping.dto.ShoppingListResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "menu-planner-service")
public interface MenuPlannerClient {

    @GetMapping("/api/v1/meal-plans/{id}")
    ShoppingListResponseDTO getMealPlanById(@PathVariable("id") Long id);
}
