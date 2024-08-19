package com.plan_menu.meal_plan_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MealPlanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealPlanServiceApplication.class, args);
    }

}
