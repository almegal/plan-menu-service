package com.plan_menu.shopping.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShoppingItem> items;

    public ShoppingList() {}

    public ShoppingList(Long userId, List<ShoppingItem> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }
}
