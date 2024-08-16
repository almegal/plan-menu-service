package com.plan_menu.meal_plan_service.mapper;

import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanEntryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit-тесты для класса {@link MapperMealPlanEntryImpl}.
 * <p>
 * Этот класс тестирует методы маппера MealPlanEntry, который преобразует объекты между
 * сущностью {@link MealPlanEntryEntity} и DTO {@link MealPlanEntryDto}.
 */
public class MapperMealPlanEntryUnitTest {

    // Тестовые данные для проверки преобразований
    private final String TITLE_RECIPE = "Арбуз";
    private final String DAY_OF_WEEK = "Понедельник";
    private final int NUMBER_OF_PORTION = 3;
    private final Long RECIPE_ID = 3123L;

    // Объект маппера, который будет тестироваться
    MapperMealPlanEntryImpl mapper = new MapperMealPlanEntryImpl();

    // Сущность и DTO, которые будут использоваться в тестах
    private MealPlanEntryEntity MEAL_PLAN_ENTRY;
    private MealPlanEntryDto MEAL_PLAN_ENTRY_DTO;

    /**
     * Инициализация тестовых данных перед каждым тестом.
     * Этот метод выполняется перед каждым тестовым методом.
     */
    @BeforeEach
    public void init() {
        // Инициализация сущности с тестовыми данными
        MEAL_PLAN_ENTRY = new MealPlanEntryEntity();
        MEAL_PLAN_ENTRY.setTitleRecipe(TITLE_RECIPE);
        MEAL_PLAN_ENTRY.setRecipeId(RECIPE_ID);
        MEAL_PLAN_ENTRY.setDayOfWeek(DAY_OF_WEEK);
        MEAL_PLAN_ENTRY.setNumberOfPeople(NUMBER_OF_PORTION);

        // Инициализация DTO с тестовыми данными
        MEAL_PLAN_ENTRY_DTO = new MealPlanEntryDto(
                RECIPE_ID,
                TITLE_RECIPE,
                DAY_OF_WEEK,
                NUMBER_OF_PORTION
        );
    }

    /**
     * Проверка того, что метод {@code mapToDto} корректно преобразует сущность в DTO.
     */
    @DisplayName("Корректное преобразование из сущности в DTO (mapToDto)")
    @Test
    public void correctConvertFromEntityToDto() {
        // Выполняем преобразование
        MealPlanEntryDto actual = mapper.mapToDto(MEAL_PLAN_ENTRY);
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTRY_DTO, actual);
    }

    /**
     * Проверка того, что метод {@code mapToEntity} корректно преобразует DTO в сущность.
     */
    @DisplayName("Корректное преобразование из DTO в сущность (mapToEntity)")
    @Test
    public void correctConvertFromDtoToEntity() {
        // Выполняем преобразование
        MealPlanEntryEntity actual = mapper.mapToEntity(MEAL_PLAN_ENTRY_DTO);
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTRY, actual);
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
