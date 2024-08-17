package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.exception.EntityNotFoundException;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlan;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanEntryImpl;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanImp;
import com.plan_menu.meal_plan_service.repository.MealPlanRepository;
import com.plan_menu.meal_plan_service.service.impl.MealPlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.plan_menu.meal_plan_service.ConfigMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit-тесты для класса {@link MealPlanService}.
 *
 * <p>Этот класс тестирует отдельные методы сервиса MealPlanService,
 * используя mock-объекты для взаимодействия с репозиторием MealPlanRepository.
 * Для создания mock-объектов и инъекции зависимостей используется библиотека Mockito.
 */
@ExtendWith(MockitoExtension.class)
public class MealPlanServiceUnitTest {

    /**
     * Mock-объект для репозитория MealPlanRepository.
     * Он будет использоваться для подмены реального взаимодействия с базой данных в тестах.
     */
    @Mock
    MealPlanRepository repository;
    /**
     * Spy-объект для маппера {@link MapperMealPlan}.
     * Позволяет частично использовать реальную логику маппинга и подменять некоторые методы в тестах.
     */
    @Spy
    MapperMealPlanEntryImpl mapperMealPlanEntry;
    @Spy
    MapperMealPlanImp mapperMealPlan;
    /**
     * Инъектируемый объект сервиса {@link MealPlanServiceImpl}, который будет тестироваться.
     * Все зависимости будут заменены на mock-объекты.
     */
    @InjectMocks
    MealPlanServiceImpl service;
    //

    @BeforeEach
    public void init() {
        // Создаем тестовые параметры
        MEAL_PLAN_ENTRY_ENTITY = new MealPlanEntryEntity();
        MEAL_PLAN_ENTRY_ENTITY.setRecipeId(RECIPE_ID);
        MEAL_PLAN_ENTRY_ENTITY.setNumberOfPeople(NUMBER_OF_PORTION);
        MEAL_PLAN_ENTRY_ENTITY.setDayOfWeek(DAY_OF_WEEK);
        MEAL_PLAN_ENTRY_ENTITY.setTitleRecipe(TITLE_RECIPE);
        //
        MEAL_PLAN_ENTITY = new MealPlanEntity();
        MEAL_PLAN_ENTITY.setUserId(USER_ID);
        MEAL_PLAN_ENTITY.setMealPlanEntryEntityList(List.of(MEAL_PLAN_ENTRY_ENTITY));
    }

    /**
     * Проверка того, что сервис возвращает корректный объект {@link MealPlanDto},
     * если такой ID существует в репозитории.
     */
    @DisplayName("Успешное получение меню-плана по валидному ID.")
    @Test
    public void getManuPlanWithCorrectId() {
        // Настройка поведения mock-объекта репозитория, чтобы он возвращал MOCK_MEAL_PLAN
        when(repository.findByUserId(anyLong())).thenReturn(Optional.of(MEAL_PLAN_ENTITY));

        // Вызов метода сервиса и получение актуального результата
        List<MealPlanEntryDto> actual = service.getMealPlan(1L);

        // Создание ожидаемого результата на основе маппера
        List<MealPlanEntryDto> expected = mapperMealPlan.mapToDto(MEAL_PLAN_ENTITY).mealPlanEntryEntityList();

        // Сравнение ожидаемого и актуального результатов
        assertEquals(expected, actual);

        // Проверка того, что метод findById в репозитории был вызван ровно один раз
        verify(repository, times(1)).findByUserId(1L);
    }

    /**
     * Проверка того, что сервис выбрасывает исключение {@link EntityNotFoundException},
     * если меню-план с данным ID не найден.
     */
    @DisplayName("Получение меню-плана по несуществующему ID")
    @Test
    public void shouldThrowWhenEntityNotFound() {
        // Подготовка значений
        String ENTITY_NOT_FOUND_MESSAGE = "Not Found";
        // Настройка поведения mock-объекта репозитория, чтобы он возвращал Optional.empty()
        when(repository.findByUserId(anyLong())).thenReturn(Optional.empty());
        // Проверка того, что вызывается исключение EntityNotFoundException при вызове метода getMealPlan
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> service.getMealPlan(MEAL_PLAN_ENTITY.getUserId()));
        // Проверка того, что сообщение исключения соответствует ожидаемому
        assertEquals(ENTITY_NOT_FOUND_MESSAGE, thrown.getMessage());
        verify(mapperMealPlan, never()).mapToDto(MEAL_PLAN_ENTITY);
    }

    @DisplayName("Успешное сохранение нового меню-плана")
    @Test
    public void saveMenuPlanSuccess() {
        // Настройка поведения мока
        when(repository.save(MEAL_PLAN_ENTITY)).thenReturn(MEAL_PLAN_ENTITY);
        when(mapperMealPlan.mapToEntity(MEAL_PLAN_ENTITY_DTO)).thenReturn(MEAL_PLAN_ENTITY);
        // Внутри метода используется приватная проверка существует пользователь или нет
        when(repository.findByUserId(anyLong())).thenReturn(Optional.empty());
        // Вызов тестируемого метода
        service.saveMealPlan(MEAL_PLAN_ENTITY_DTO);
        // Проверка вызова маппера
        verify(mapperMealPlan, times(1)).mapToEntity(MEAL_PLAN_ENTITY_DTO);
        verify(mapperMealPlanEntry, times(1)).mapToEntity(any(MealPlanEntryDto.class));
        verify(repository, times(1)).save(MEAL_PLAN_ENTITY);
    }
}
