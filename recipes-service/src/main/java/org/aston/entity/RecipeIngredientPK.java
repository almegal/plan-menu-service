package org.aston.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class RecipeIngredientPK implements Serializable {
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "product_id")
    private Long productId;
}
