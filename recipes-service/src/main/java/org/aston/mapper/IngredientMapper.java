package org.aston.mapper;

import org.aston.dto.IngredientDTO;
import org.aston.entity.RecipeIngredients;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientMapper {
    public IngredientDTO mapEntityToDto(RecipeIngredients ingredients) {
        if ( ingredients == null ) {
            return null;
        }

        return new IngredientDTO(ingredients.getId().getProductId(),
                ingredients.getAmount());

    }

    public List<IngredientDTO> mapEntitiesToDtos(List<RecipeIngredients> ingredientsList) {
        if ( ingredientsList == null ) {
            return null;
        }

        List<IngredientDTO> list = new ArrayList<>( ingredientsList.size() );
        for (RecipeIngredients recipeIngredients : ingredientsList) {
            list.add(mapEntityToDto( recipeIngredients));
        }
        return list;
    }
}
