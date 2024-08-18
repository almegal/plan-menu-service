package com.plan_menu.meal_plan_service.service.impl;

import com.plan_menu.meal_plan_service.component.ShopListMaker;
import com.plan_menu.meal_plan_service.dto.*;
import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import com.plan_menu.meal_plan_service.entity.MealPlanEntryEntity;
import com.plan_menu.meal_plan_service.exception.EntityNotFoundException;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlan;
import com.plan_menu.meal_plan_service.mapper.MapperMealPlanEntry;
import com.plan_menu.meal_plan_service.repository.MealPlanRepository;
import com.plan_menu.meal_plan_service.service.MealPlanService;
import com.plan_menu.meal_plan_service.service.NotificationService;
import com.plan_menu.meal_plan_service.service.OrderService;
import com.plan_menu.meal_plan_service.service.RecipeService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса MealPlanService, предоставляющего методы для работы с планами питания.
 */
@Service
public class MealPlanServiceImpl implements MealPlanService {

    // Репозиторий для работы с сущностями MealPlan в базе данных.
    private final MealPlanRepository repository;

    // Маппер для преобразования между сущностью MealPlanEntity и DTO MealPlanDto.
    private final MapperMealPlan mapperMealPlan;

    // Маппер для преобразования между сущностью MealPlanEntryEntity и DTO MealPlanEntryDto.
    private final MapperMealPlanEntry mapperMealPlanEntry;

    // Сервис для работы с рецептами.

    private final RecipeService recipeService;

    // Сервис для оформления заказов.
    private final OrderService orderService;

    // Сервис для отправки уведомлений.
    private final NotificationService notificationService;

    // Компонент для создания списка покупок.
    private final ShopListMaker shopListMaker;

    public MealPlanServiceImpl(MealPlanRepository repository,
                               MapperMealPlan mapperMealPlan,
                               MapperMealPlanEntry mapperMealPlanEntry,
                               RecipeService recipeService,
                               OrderService orderService,
                               NotificationService notificationService,
                               ShopListMaker shopListMaker) {
        this.repository = repository;
        this.mapperMealPlan = mapperMealPlan;
        this.mapperMealPlanEntry = mapperMealPlanEntry;
        this.recipeService = recipeService;
        this.orderService = orderService;
        this.notificationService = notificationService;
        this.shopListMaker = shopListMaker;
    }

    /**
     * Конструктор для инъекции зависимостей.
     *
     * @param repository          репозиторий для работы с сущностями MealPlan
     * @param mapperMealPlan      маппер для преобразования между MealPlanEntity и MealPlanDto
     * @param mapperMealPlanEntry маппер для преобразования между MealPlanEntryEntity и MealPlanEntryDto
     */


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
        // Проверяем, существует ли план меню для данного пользователя
        if (checkIfExistUser(mealPlan.userId())) {
            throw new RuntimeException("Plan menu for user already exist");
        }

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

        // Получаем рецепты из сервиса рецептов
        List<RecipeWithIngredientDto> ListRecipesWithIngredientDto;
        try {
            // Извлекаем идентификаторы рецептов из плана питания
            List<Long> ids = getIdsFromMealPlanEntry(mealPlanEntity.getMealPlanEntryEntityList());
            // Получаем полные данные о рецептах
            ListRecipesWithIngredientDto = recipeService.getRecipes(ids);
        } catch (FeignException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        // Формируем список покупок
        ShoppingListRequestDto shoppMap = shopListMaker.createShoppingList(mealPlan.userId(), ListRecipesWithIngredientDto, mealPlan.mealPlanEntryEntityList());

        // Отправляем список на заказ
        HttpStatus orderHttpStatus;
        try {
            orderHttpStatus = orderService.makeOrder(shoppMap);
        } catch (FeignException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        // В зависимости от результата заказа, отправляем уведомление пользователю
        if (orderHttpStatus.is2xxSuccessful()) {
            notificationService.sendNotification(new NotificationDto(
                    mealPlan.userId(),
                    "Plan saved and product is ordering"
            ));
        } else {
            notificationService.sendNotification(new NotificationDto(
                    mealPlan.userId(),
                    "Something went wrong"
            ));
        }
    }

    /**
     * Проверяет, существует ли план питания для пользователя.
     *
     * @param userId идентификатор пользователя
     * @return true, если план питания существует, иначе false
     * @throws RuntimeException если произошла ошибка при проверке
     */
    private boolean checkIfExistUser(Long userId) throws RuntimeException {
        try {
            repository.findByUserId(userId).orElseThrow();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Извлекает список идентификаторов рецептов из записей плана питания.
     *
     * @param entry список записей плана питания
     * @return список идентификаторов рецептов
     */
    private List<Long> getIdsFromMealPlanEntry(List<MealPlanEntryEntity> entry) {
        return entry.stream().map(MealPlanEntryEntity::getRecipeId).toList();
    }
}
