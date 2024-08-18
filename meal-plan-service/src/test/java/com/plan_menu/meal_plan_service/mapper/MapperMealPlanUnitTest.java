package com.plan_menu.meal_plan_service.mapper;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanEntryImpl;
import com.plan_menu.meal_plan_service.mapper.impl.MapperMealPlanImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.plan_menu.meal_plan_service.ConfigMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MapperMealPlanUnitTest {

    @Spy
    MapperMealPlanEntryImpl mapperMealPlanEntry;

    @InjectMocks
    MapperMealPlanImp mapper;

    @BeforeEach
    public void init() {
        //
        MEAL_PLAN_ENTRY_ENTITY = new MealPlanEntryEntity();
        MEAL_PLAN_ENTRY_ENTITY.setRecipeId(RECIPE_ID);
        MEAL_PLAN_ENTRY_ENTITY.setNumberOfPeople(NUMBER_OF_PORTION);
        MEAL_PLAN_ENTRY_ENTITY.setDayOfWeek(DAY_OF_WEEK);
        MEAL_PLAN_ENTRY_ENTITY.setTitleRecipe(TITLE_RECIPE);
        //
        List<MealPlanEntryEntity> mealPlanEntryEntityList = List.of(MEAL_PLAN_ENTRY_ENTITY);
        //
        MEAL_PLAN_ENTITY = new MealPlanEntity();
        MEAL_PLAN_ENTITY.setId(null);
        MEAL_PLAN_ENTITY.setUserId(USER_ID);
        MEAL_PLAN_ENTITY.setMealPlanEntryEntityList(mealPlanEntryEntityList);

    }

    @DisplayName("Корректное преобразование из сущности в DTO (mapToDto)")
    @Test
    public void correctConvertFromEntityToDto() {
        // Выполняем преобразование
        MealPlanDto actual = mapper.mapToDto(MEAL_PLAN_ENTITY);
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTITY_DTO, actual);
    }

    @DisplayName("Корректное преобразование из DTO в сущность (mapToEntity)")
    @Test
    public void correctConvertFromDtoToEntity() {
        // Выполняем преобразование
        MealPlanEntity actual = mapper.mapToEntity(MEAL_PLAN_ENTITY_DTO);
        //
        MEAL_PLAN_ENTITY.setMealPlanEntryEntityList(List.of());
        // Сравниваем результат с ожидаемым
        assertEquals(MEAL_PLAN_ENTITY, actual);
    }

}
