package org.aston.dto;

public record ResponseRecipeDTO (
        Long recipeId,
        String recipeTitle,
        Integer timeForCook
) {}
