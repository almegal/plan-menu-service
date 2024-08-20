package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.exception.EntityNotFoundException;

import java.util.List;

public interface MealPlanService {
    /**
     * Получает план питания для пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return список записей плана питания в формате DTO
     * @throws EntityNotFoundException если план питания не найден
     */
    List<MealPlanEntryDto> getMealPlan(Long userId);

    /**
     * Сохраняет новый план питания для пользователя.
     * Если план питания для пользователя уже существует, выбрасывается исключение.
     * Также оформляется заказ на продукты по плану питания и отправляется уведомление.
     *
     * @param dto план питания в формате DTO
     * @throws RuntimeException если план питания для пользователя уже существует или возникла ошибка при взаимодействии с внешними сервисами
     */
    void saveMealPlan(MealPlanDto dto);


}
