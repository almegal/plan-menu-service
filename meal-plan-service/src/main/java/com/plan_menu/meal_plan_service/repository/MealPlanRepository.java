package com.plan_menu.meal_plan_service.repository;

import com.plan_menu.meal_plan_service.entity.MealPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlanEntity, Long> {
    @Query("SELECT mp FROM MealPlanEntity mp LEFT JOIN FETCH mp.mealPlanEntryEntityList mpe WHERE mp.userId = :userId")
    Optional<MealPlanEntity> findByUserId(@Param("userId") Long userId);
}
