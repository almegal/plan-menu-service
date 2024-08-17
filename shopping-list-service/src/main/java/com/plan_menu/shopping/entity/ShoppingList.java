package com.plan_menu.shopping.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Сущность, представляющая список покупок в базе данных.
 * Содержит идентификатор списка покупок, идентификатор пользователя, название,
 * список товаров и дату создания.
 */
@Data
@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String name;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListItem> items;

    private Date createdDate;
}
