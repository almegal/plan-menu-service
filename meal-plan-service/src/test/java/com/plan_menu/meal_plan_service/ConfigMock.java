package com.plan_menu.meal_plan_service;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;

import java.util.List;

public class ConfigMock {

    // Тестовые данные для проверки преобразований
    public static final String TITLE_RECIPE = "Арбуз";
    public static final String DAY_OF_WEEK = "Понедельник";
    public static final int NUMBER_OF_PORTION = 3;
    public static final Long RECIPE_ID = 3123L;
    public static final Long USER_ID = 4091L;

    // Сущность и DTO, которые будут использоваться в тестах
    public static MealPlanEntryEntity MEAL_PLAN_ENTRY_ENTITY;
    public static MealPlanEntryDto MEAL_PLAN_ENTRY_ENTITY_DTO = new MealPlanEntryDto(
            RECIPE_ID,
            TITLE_RECIPE,
            DAY_OF_WEEK,
            NUMBER_OF_PORTION
    );
    //
    public static MealPlanDto MEAL_PLAN_ENTITY_DTO = new MealPlanDto(
            USER_ID,
            List.of(MEAL_PLAN_ENTRY_ENTITY_DTO)
    );
    public static MealPlanEntity MEAL_PLAN_ENTITY;

}
