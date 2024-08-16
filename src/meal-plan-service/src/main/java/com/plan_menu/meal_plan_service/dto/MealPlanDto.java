package com.plan_menu.meal_plan_service.dto;

import java.util.List;

/**
 * Объект передачи данных (DTO), представляющий план питания.
 * Этот класс является неизменяемым и используется для передачи данных, связанных с планом питания пользователя.
 *
 * @param userId         Уникальный идентификатор пользователя, связанного с планом питания.
 * @param mealPlanEntry  Список объектов MealPlanEntryDto, представляющих отдельные записи плана питания.
 */
public record MealPlanDto(
        Long userId,
        List<MealPlanEntryDto> mealPlanEntry
) {
}
