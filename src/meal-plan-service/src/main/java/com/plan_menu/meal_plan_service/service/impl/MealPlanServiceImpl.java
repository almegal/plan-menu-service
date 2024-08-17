package com.plan_menu.meal_plan_service.service.impl;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.exception.EntityNotFoundException;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlan;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlanEntry;
import com.plan_menu.meal_plan_service.repository.MealPlanRepository;
import com.plan_menu.meal_plan_service.service.MealPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    // Репозиторий для работы с сущностями MealPlan в базе данных.
    private final MealPlanRepository repository;

    // Маппер для преобразования между сущностью MealPlanEntity и DTO MealPlanDto.
    private final MapperMealPlan mapperMealPlan;

    // Маппер для преобразования между сущностью MealPlanEntryEntity и DTO MealPlanEntryDto.
    private final MapperMealPlanEntry mapperMealPlanEntry;

    // Конструктор для инъекции зависимостей.
    public MealPlanServiceImpl(MealPlanRepository repository,
                               MapperMealPlan mapperMealPlan,
                               MapperMealPlanEntry mapperMealPlanEntry) {
        this.repository = repository;
        this.mapperMealPlan = mapperMealPlan;
        this.mapperMealPlanEntry = mapperMealPlanEntry;
    }

    @Override
    public List<MealPlanEntryDto> getMealPlan(Long userId) {
        // Получаем опциональную сущность MealPlanEntity из репозитория по userId.
        Optional<MealPlanEntity> mealPlanEntityOptional = repository.findByUserId(userId);

        // Если сущность не найдена, выбрасываем исключение EntityNotFoundException.
        MealPlanEntity mealPlanEntity = mealPlanEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("Not Found")
        );

        // Преобразуем сущность в DTO и возвращаем.
        return mapperMealPlan.mapToDto(mealPlanEntity).mealPlanEntryEntityList();
    }

    @Override
    @Transactional
    public void saveMealPlan(MealPlanDto mealPlan) {
        // Проверяем существует ли план меню для такого пользователя
        checkIfExistUser(mealPlan.userId());
        // Преобразуем DTO в сущность MealPlanEntity.
        MealPlanEntity mealPlanEntity = mapperMealPlan.mapToEntity(mealPlan);

        // Преобразуем список DTO MealPlanEntryDto в список сущностей MealPlanEntryEntity.
        // Для каждой сущности MealPlanEntryEntity устанавливаем связь с MealPlanEntity.
        List<MealPlanEntryEntity> mealPlanEntryEntityList = mealPlan.mealPlanEntryEntityList()
                .stream()
                .map(e -> {
                    MealPlanEntryEntity entryEntity = mapperMealPlanEntry.mapToEntity(e);
                    entryEntity.setMealPlan(mealPlanEntity);
                    return entryEntity;
                })
                .toList();

        // Устанавливаем список MealPlanEntryEntity в сущность MealPlanEntity.
        mealPlanEntity.setMealPlanEntryEntityList(mealPlanEntryEntityList);

        // Сохраняем MealPlanEntity в репозитории.
        repository.save(mealPlanEntity);
    }

    // Проверяем существует такой пользователь или нет
    // при необходимости выбрасываем исключение
    private void checkIfExistUser(Long userId) throws RuntimeException {
        repository.findByUserId(userId).orElseThrow();
    }
}
