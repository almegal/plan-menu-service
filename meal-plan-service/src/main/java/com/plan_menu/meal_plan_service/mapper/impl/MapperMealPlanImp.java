package com.plan_menu.meal_plan_service.mapper.impl;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperMealPlanImp implements MapperMealPlan {

    @Override
    public MealPlanDto mapToDto(MealPlanEntity entity) {
        // Преобразуем список MealPlanEntryEntity в список MealPlanEntryDto
        List<MealPlanEntryDto> mealPlanEntryDto = entity.getMealPlanEntryEntityList()
                .stream()
                // Для каждого элемента списка сущностей создаем соответствующий DTO
                .map(e -> new MealPlanEntryDto(
                        e.getRecipeId(),
                        e.getTitleRecipe(),
                        e.getDayOfWeek(),
                        e.getNumberOfPeople()
                ))
                .toList();

        // Создаем и возвращаем новый объект MealPlanDto с преобразованными данными
        return new MealPlanDto(
                entity.getUserId(),
                mealPlanEntryDto
        );
    }

    @Override
    public MealPlanEntity mapToEntity(MealPlanDto dto) {
        // Создаем новый объект сущности MealPlanEntity
        MealPlanEntity mealPlanEntity = new MealPlanEntity();
        // Устанавливаем userId в сущности
        mealPlanEntity.setUserId(dto.userId());
        mealPlanEntity.setMealPlanEntryEntityList(List.of());
        // Возвращаем объект сущности с преобразованными данными
        return mealPlanEntity;
    }
}
