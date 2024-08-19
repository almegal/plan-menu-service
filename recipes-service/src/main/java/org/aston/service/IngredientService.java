package org.aston.service;

import org.aston.dto.IngredientDTO;
import lombok.RequiredArgsConstructor;
import org.aston.mapper.IngredientMapper;
import org.springframework.stereotype.Service;
import org.aston.repository.IngredientRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientMapper mapper;
    private final IngredientRepo ingredientRepository;
    public List<IngredientDTO> getIngredientsByRecipeId(long recipeId) {
        return mapper.mapEntitiesToDtos(ingredientRepository.findByRecipeRecipeId(recipeId));
    }
}
