package com.plan_menu.shopping.entity;

import com.plan_menu.shopping.enums.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Сущность, представляющая товар в списке покупок в базе данных.
 * Содержит идентификатор товара, идентификатор списка покупок, идентификатор продукта,
 * количество и единицу измерения.
 */
@Data
@Entity
public class ShoppingListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;
}
