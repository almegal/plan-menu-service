package org.aston.controller;

import org.aston.dto.RecipeWithIngredientDTO;
import org.aston.dto.ResponseCategoriesDTO;
import org.aston.dto.ResponseRecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.aston.service.CategoryService;
import org.aston.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<ResponseRecipeDTO>> getRecipesByIds(@RequestParam("ids") List<Long> recipeIds) {
        List<ResponseRecipeDTO> responseDTOs = recipeService.getRecipesByIds(recipeIds);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/with/ingredient")
    public ResponseEntity<List<RecipeWithIngredientDTO>> getRecipeWithIngredientsByIds(
            @RequestParam("ids") List<Long> recipeWithIngredientIds) {
        List<RecipeWithIngredientDTO> responseDTOs =
                recipeService.getRecipesWithIngredientsByIds(recipeWithIngredientIds);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ResponseRecipeDTO>> getRecipesByCategoryId(@PathVariable String id) {
        long categoryId = Long.parseLong(id);
        List<ResponseRecipeDTO> responseDTOs = recipeService.getRecipesByCategoryId(categoryId);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/recipes/category")
    public ResponseEntity<ResponseCategoriesDTO> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
