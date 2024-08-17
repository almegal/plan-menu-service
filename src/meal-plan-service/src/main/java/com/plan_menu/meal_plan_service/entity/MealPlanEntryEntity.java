package com.plan_menu.meal_plan_service.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Сущность, представляющая запись в плане питания.
 * Эта сущность используется для хранения информации о конкретном рецепте в базе данных.
 */
@Entity
public class MealPlanEntryEntity {

    /**
     * Уникальный идентификатор записи в плане питания.
     * Генерируется автоматически при сохранении в базу данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название рецепта, связанного с данной записью в плане питания.
     */
    @Column(name = "recipe_title")
    private String titleRecipe;

    /**
     * День недели, на который запланировано приготовление данного рецепта.
     */
    @Column(name = "week_day")
    private String dayOfWeek;

    /**
     * Количество порций, на которое рассчитан данный рецепт.
     */
    @Column(name = "servings")
    private Integer numberOfPeople;

    /**
     * Уникальный идентификатор рецепта.
     * Этот идентификатор связывает запись в плане питания с конкретным рецептом.
     */
    @Column(name = "recipe_id")
    private Long recipeId;

    /**
     * Пустой конструктор по умолчанию.
     * Используется фреймворками для создания экземпляров сущности.
     */
    public MealPlanEntryEntity() {
    }

    // Геттеры и сеттеры для полей

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleRecipe() {
        return titleRecipe;
    }

    public void setTitleRecipe(String titleRecipe) {
        this.titleRecipe = titleRecipe;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealPlanEntryEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(titleRecipe, that.titleRecipe) && Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(numberOfPeople, that.numberOfPeople) && Objects.equals(recipeId, that.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleRecipe, dayOfWeek, numberOfPeople, recipeId);
    }
}
