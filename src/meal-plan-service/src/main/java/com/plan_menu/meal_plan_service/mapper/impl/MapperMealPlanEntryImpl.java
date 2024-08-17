package com.plan_menu.meal_plan_service.mapper.impl;

import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlanEntry;
import org.springframework.stereotype.Component;

/**
 * Реализация интерфейса MapperMealPlanEntry для преобразования
 * между сущностью MealPlanEntryEntity и DTO MealPlanEntryDto.
 */
@Component
public class MapperMealPlanEntryImpl implements MapperMealPlanEntry {

    @Override
    public MealPlanEntryDto mapToDto(MealPlanEntryEntity entity) {
        if (entity == null) return null;
        // Преобразование данных из сущности в DTO
        return new MealPlanEntryDto(
                entity.getRecipeId(),
                entity.getTitleRecipe(),
                entity.getDayOfWeek(),
                entity.getNumberOfPeople()
        );
    }

    @Override
    public MealPlanEntryEntity mapToEntity(MealPlanEntryDto dto) {
        if (dto == null) return null;
        // Создание новой сущности и заполнение её данными из DTO
        MealPlanEntryEntity entryEntity = new MealPlanEntryEntity();

        entryEntity.setDayOfWeek(dto.dayOfWeek());
        entryEntity.setRecipeId(dto.recipeId());
        entryEntity.setTitleRecipe(dto.titleRecipe());
        entryEntity.setNumberOfPeople(dto.numberOfPeople());

        return entryEntity;
    }
}
