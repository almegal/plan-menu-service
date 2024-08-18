package com.plan_menu.meal_plan_service.service.impl;

import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;
import com.plan_menu.meal_plan_service.feign.RecipeServiceClient;
import com.plan_menu.meal_plan_service.service.RecipeService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeServiceClient client;

    public RecipeServiceImpl(RecipeServiceClient client) {
        this.client = client;
    }

    //
    @Override
    public List<RecipeWithIngredientDto> getRecipes(List<Long> ids) throws FeignException {
        return client.getListRecipes(ids);
    }
}
