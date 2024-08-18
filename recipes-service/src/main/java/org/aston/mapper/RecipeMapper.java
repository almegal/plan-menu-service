package org.aston.mapper;

import org.aston.dto.ResponseRecipeDTO;
import org.aston.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public ResponseRecipeDTO entityToDto(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }
        Long recipeId = recipe.getRecipeId();
        String recipeTitle = recipe.getRecipeTitle();
        Integer timeForCook = recipe.getTimeForCook();

        return new ResponseRecipeDTO( recipeId, recipeTitle, timeForCook );
    }
}
