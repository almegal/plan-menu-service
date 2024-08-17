package com.plan_menu.meal_plan_service.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Сущность, представляющая план питания пользователя.
 */
@Entity
public class MealPlanEntity {

    /**
     * Уникальный идентификатор плана питания.
     * Генерируется автоматически при сохранении в базу данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_plan_id")
    private Long id;

    /**
     * Список записей плана питания, связанных с этим планом.
     * Используется каскадное сохранение и удаление записей (orphanRemoval = true).
     * Каждая запись привязана к этому плану через внешний ключ.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MealPlanEntryEntity_id")
    private List<MealPlanEntryEntity> mealPlanEntryEntityList;

    /**
     * Идентификатор пользователя, к которому относится данный план питания.
     */
    @Column(name = "user_id")
    private Long userId;

    // Пустой конструктор и геттеры/сеттеры без комментариев
    public MealPlanEntity() {
    }

    public List<MealPlanEntryEntity> getMealPlanEntryEntityList() {
        return mealPlanEntryEntityList;
    }

    public void setMealPlanEntryEntityList(List<MealPlanEntryEntity> mealPlanEntryEntityList) {
        this.mealPlanEntryEntityList = mealPlanEntryEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealPlanEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(mealPlanEntryEntityList, that.mealPlanEntryEntityList) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mealPlanEntryEntityList, userId);
    }
}
