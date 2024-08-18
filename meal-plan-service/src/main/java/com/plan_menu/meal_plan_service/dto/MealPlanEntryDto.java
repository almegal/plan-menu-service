package com.plan_menu.meal_plan_service.dto;

/**
 * Объект передачи данных (DTO), представляющий запись в плане питания.
 * Этот класс является неизменяемым и используется для передачи данных, связанных с конкретным блюдом в плане питания.
 *
 * @param recipeId       Уникальный идентификатор рецепта.
 * @param titleRecipe    Название рецепта.
 * @param dayOfWeek      День недели, на который запланировано приготовление этого рецепта.
 * @param numberOfPeople Количество людей, на которых рассчитан рецепт.
 */
public record MealPlanEntryDto(
        Long recipeId,
        String titleRecipe,
        String dayOfWeek,
        Integer numberOfPeople
) {
}
