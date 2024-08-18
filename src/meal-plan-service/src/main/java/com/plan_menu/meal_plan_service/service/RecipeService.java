package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;

import java.util.List;

public interface RecipeService {
    public List<RecipeWithIngredientDto> getRecipes(List<Long> ids);
}
