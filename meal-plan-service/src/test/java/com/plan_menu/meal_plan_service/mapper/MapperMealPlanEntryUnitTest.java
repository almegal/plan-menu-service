package com.plan_menu.meal_plan_service.mapper;

import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanEntryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static com.plan_menu.meal_plan_service.ConfigMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit-тесты для класса {@link MapperMealPlanEntryImpl}.
 * <p>
 * Этот класс тестирует методы маппера MealPlanEntry, который преобразует объекты между
 * сущностью {@link MealPlanEntryEntity} и DTO {@link MealPlanEntryDto}.
 */
public class MapperMealPlanEntryUnitTest {
    // Объект маппера, который будет тестироваться
    MapperMealPlanEntryImpl mapper = new MapperMealPlanEntryImpl();

    /**
     * Инициализация тестовых данных перед каждым тестом.
     * Этот метод выполняется перед каждым тестовым методом.
     */
    @BeforeEach
    public void init() {
        // Инициализация сущности с тестовыми данными
        MEAL_PLAN_ENTRY_ENTITY = new MealPlanEntryEntity();
        MEAL_PLAN_ENTRY_ENTITY.setTitleRecipe(TITLE_RECIPE);
        MEAL_PLAN_ENTRY_ENTITY.setRecipeId(RECIPE_ID);
        MEAL_PLAN_ENTRY_ENTITY.setDayOfWeek(DAY_OF_WEEK);
        MEAL_PLAN_ENTRY_ENTITY.setNumberOfPeople(NUMBER_OF_PORTION);
    }

    /**
     * Проверка того, что метод {@code mapToDto} корректно преобразует сущность в DTO.
     */
    @DisplayName("Корректное преобразование из сущности в DTO (mapToDto)")
    @Test
    public void correctConvertFromEntityToDto() {
        // Выполняем преобразование
        MealPlanEntryDto actual = mapper.mapToDto(MEAL_PLAN_ENTRY_ENTITY);
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTRY_ENTITY_DTO, actual);
    }

    /**
     * Проверка того, что метод {@code mapToEntity} корректно преобразует DTO в сущность.
     */
    @DisplayName("Корректное преобразование из DTO в сущность (mapToEntity)")
    @Test
    public void correctConvertFromDtoToEntity() {
        // Выполняем преобразование
        MealPlanEntryEntity actual = mapper.mapToEntity(MEAL_PLAN_ENTRY_ENTITY_DTO);
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTRY_ENTITY, actual);
    }

    /**
     * Проверка обработки null-значений при преобразовании из DTO в сущность.
     * Убедимся, что метод {@code mapToEntity} возвращает null, если в параметре передано null.
     */
    @DisplayName("Обработка null-значений  (mapToEntity)")
    @ParameterizedTest
    @NullSource
    public void mapToEntityShouldReturnNullWhenSetInParam(MealPlanEntryDto source) {
        // Выполняем преобразование и проверяем результат
        MealPlanEntryEntity actual = mapper.mapToEntity(source);
        assertNull(actual);
    }

    /**
     * Проверка обработки null-значений при преобразовании из сущности в DTO.
     * Убедимся, что метод {@code mapToDto} возвращает null, если в параметре передано null.
     */
    @DisplayName("Обработка null-значений (mapToDto)")
    @ParameterizedTest
    @NullSource
    public void mapToDtoShouldReturnNullWhenSetInParam(MealPlanEntryEntity source) {
        // Выполняем преобразование и проверяем результат
        MealPlanEntryDto actual = mapper.mapToDto(source);
        assertNull(actual);
    }
}
