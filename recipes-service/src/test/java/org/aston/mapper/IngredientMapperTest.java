package org.aston.mapper;

import org.aston.dto.IngredientDTO;
import org.aston.entity.RecipeIngredientPK;
import org.aston.entity.RecipeIngredients;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientMapperTest {

    private final IngredientMapper ingredientMapper = new IngredientMapper();

    @Test
    @DisplayName("Test mapping ingredient entity to dto")
    void testMapEntityToDto() {
        RecipeIngredients entity = new RecipeIngredients(new RecipeIngredientPK(1L, 1L),
                1.0, "Яйцо");

        IngredientDTO result = ingredientMapper.mapEntityToDto(entity);

        assertEquals(1L, result.productId());
        assertEquals(1.0, result.amount());
        assertEquals("Яйцо", result.ingredientTitle());
    }

    @Test
    @DisplayName("Test mapping ingredient entities to dtos")
    void mapEntitiesToDtos() {
        List<RecipeIngredients> entities = new ArrayList<>();
        entities.add(new RecipeIngredients(new RecipeIngredientPK(1L, 1L),
                1.0, "Яйцо"));
        entities.add(new RecipeIngredients(new RecipeIngredientPK(1L, 2L),
                100.0, "Молоко"));

        List<IngredientDTO> result = ingredientMapper.mapEntitiesToDtos(entities);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).productId());
        assertEquals(1.0, result.get(0).amount());
        assertEquals("Яйцо", result.get(0).ingredientTitle());
        assertEquals(2L, result.get(1).productId());
        assertEquals(100.0, result.get(1).amount());
        assertEquals("Молоко", result.get(1).ingredientTitle());
    }
}
