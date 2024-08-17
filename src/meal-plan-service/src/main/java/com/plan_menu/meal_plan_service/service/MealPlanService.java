package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;

import java.util.List;

public interface MealPlanService {
    List<MealPlanEntryDto> getMealPlan(Long userId);

    void saveMealPlan(MealPlanDto dto);


}
