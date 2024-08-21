package com.plan_menu.meal_plan_service.component;

import com.plan_menu.meal_plan_service.dto.MealPlanEntryDto;
import com.plan_menu.meal_plan_service.dto.RecipeWithIngredientDto;
import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//@Scope("request")
public class ShopListMaker {

//    private final Map<String, Double> SHOPP_MAPS = new HashMap<>();

    //
    public ShoppingListRequestDto createShoppingList(
            Long userId,
            List<RecipeWithIngredientDto> recipeWithIngredientDto,
            List<MealPlanEntryDto> mealPlansEntryDto
    ) {

        //
        Map<String, Double> shoppMaps = new HashMap<>();

        //
        recipeWithIngredientDto.forEach(
                recipe -> {
                    //
                    MealPlanEntryDto mealPlanEntryDto = getMealPlanEntryByTitleRecipe(recipe.recipeTitle(), mealPlansEntryDto);
                    //
                    int numberOfPeople = mealPlanEntryDto.numberOfPeople();
                    //
                    setShoppListForRecipe(recipe, numberOfPeople, shoppMaps);
                }
        );

        //
        return new ShoppingListRequestDto(
                userId,
                shoppMaps
        );
    }

    //
    private MealPlanEntryDto getMealPlanEntryByTitleRecipe(String s, List<MealPlanEntryDto> mealPlansEntryDto) {
        return mealPlansEntryDto.stream()
                .filter(e -> e.titleRecipe().equals(s))
                .toList()
                .getFirst();
    }

    //
    private void setShoppListForRecipe(RecipeWithIngredientDto recipeWithIngredientDto,
                                       int numberOfPeople,
                                       Map<String, Double> map) {
        //
        recipeWithIngredientDto.ingredientDTOs()
                //
                .forEach(e -> {
                    String productName = e.ingredientTitle();
                    Double count = e.amount() * numberOfPeople;
                    map.put(productName, count);
                });
    }
}
