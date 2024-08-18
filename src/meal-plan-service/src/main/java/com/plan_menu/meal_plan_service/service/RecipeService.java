package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;
import feign.FeignException;

import java.util.List;

/**
 * Интерфейс для сервиса рецептов, предоставляющий метод для получения информации о рецептах на основе их идентификаторов.
 */
public interface RecipeService {

    /**
     * Получает список рецептов с ингредиентами на основе переданных идентификаторов.
     *
     * @param ids список идентификаторов рецептов, которые необходимо получить.
     * @return список объектов RecipeWithIngredientDto, содержащих информацию о рецептах и их ингредиентах.
     * @throws FeignException в случае возникновения ошибки при обращении к внешнему сервису рецептов.
     */
    List<RecipeWithIngredientDto> getRecipes(List<Long> ids) throws FeignException;
}
