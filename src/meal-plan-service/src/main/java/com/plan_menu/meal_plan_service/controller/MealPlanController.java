package com.plan_menu.meal_plan_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealPlanController {

    @GetMapping("/menu-plan/{userId}")
    public ResponseEntity<String> getMenuPlan(@PathVariable Long userId) {
        return ResponseEntity.ok("Получен план меню пользователя " + userId);
    }

    @PostMapping("/menu-plan/")
    public HttpStatus postMenuPlan(String someString) {
        return HttpStatus.OK;
    }
}
