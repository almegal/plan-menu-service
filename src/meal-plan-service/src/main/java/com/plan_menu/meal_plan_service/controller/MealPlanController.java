package com.plan_menu.meal_plan_service.controller;

import com.plan_menu.meal_plan_service.dto.MealPlanDto;
import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.service.MealPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealPlanController {
    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @GetMapping("/menu-plan/{userId}")
    public ResponseEntity<List<MealPlanEntryDto>> getMenuPlan(@PathVariable Long userId) {
        try {
            List<MealPlanEntryDto> mealPlanDto = mealPlanService.getMealPlan(userId);
            return ResponseEntity.ok(mealPlanDto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/menu-plan/")
    public ResponseEntity<String> postMenuPlan(@RequestBody MealPlanDto mealPlanDto) {
        try {
            mealPlanService.saveMealPlan(mealPlanDto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
