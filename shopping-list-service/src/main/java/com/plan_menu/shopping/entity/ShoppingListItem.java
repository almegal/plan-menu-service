package com.plan_menu.shopping.entity;

import com.plan_menu.shopping.enums.MeasurementUnit;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

/**
 * Сущность, представляющая элемент списка покупок.
 * Содержит информацию об элементе списка покупок, такую как идентификатор, продукт,
 * количество, единица измерения и связь с родительским списком покупок.
 */
@Data
@Entity
@Table(name = "shopping_list_items")
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private ShoppingList shoppingList;

    @Transient
    private List<Product> optimizedProducts;

    public List<Product> getOptimizedProducts() {
        return optimizedProducts;
    }

    public void setOptimizedProducts(List<Product> optimizedProducts) {
        this.optimizedProducts = optimizedProducts;
    }
}
