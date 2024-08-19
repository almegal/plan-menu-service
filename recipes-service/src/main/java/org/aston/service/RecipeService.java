package org.aston.service;

import org.aston.dto.IngredientDTO;
import org.aston.dto.RecipeWithIngredientDTO;
import org.aston.dto.ResponseRecipeDTO;
import org.aston.entity.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.mapper.RecipeMapper;
import org.springframework.stereotype.Service;
import org.aston.repository.RecipeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
    private final RecipeRepo recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientService ingredientService;

    public List<ResponseRecipeDTO> getRecipesByIds(List<Long> ids) {
        return ids.parallelStream()
                .map(id -> recipeMapper.entityToDto(recipeRepository.findById(id).orElseThrow()))
                .collect(Collectors.toList());
    }

    public List<RecipeWithIngredientDTO> getRecipesWithIngredientsByIds(List<Long> recipesIds) {
        List<Recipe> recipes = recipeRepository.findAllById(recipesIds);

        List<IngredientDTO> ingredientDTOS;
        List<RecipeWithIngredientDTO> recipesWithIngredientsDTOs = new ArrayList<>();
        for(Recipe recipe : recipes) {
            ingredientDTOS = ingredientService.getIngredientsByRecipeId(recipe.getRecipeId());
            recipesWithIngredientsDTOs.add(new RecipeWithIngredientDTO(recipe.getRecipeId(),
                    recipe.getRecipeTitle(), ingredientDTOS));
        }
        return recipesWithIngredientsDTOs;
    }

    public List<ResponseRecipeDTO> getRecipesByCategoryId(long categoryId) {
        List<Recipe> recipes = recipeRepository.findByCategoryCategoryId(categoryId);
        return recipes.stream()
                .map(recipeMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
