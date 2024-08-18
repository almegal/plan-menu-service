package org.aston.repository;

import org.aston.entity.RecipeIngredientPK;
import org.aston.entity.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepo extends JpaRepository<RecipeIngredients, RecipeIngredientPK> {
    List<RecipeIngredients> findByRecipeRecipeId(long recipeId);
}
