package org.aston.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = RecipeIngredients.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecipeIngredients {
    public static final String TABLE_NAME = "recipe_ingredients";

    @EmbeddedId
    private RecipeIngredientPK id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "ingredient_title")
    private String ingredientTitle;
}
