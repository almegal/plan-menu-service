package com.plan_menu.meal_plan_service.feign;

import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="${app.feign.config.name.recipe}")
public interface RecipeServiceClient {
    @GetMapping("/recipes/{ids}")
    List<RecipeWithIngredientDto> getListRecipes(List<Long> ids);
}
