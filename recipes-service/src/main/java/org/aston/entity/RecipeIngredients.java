package org.aston.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
