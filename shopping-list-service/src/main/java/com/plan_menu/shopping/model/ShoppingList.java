package com.plan_menu.shopping.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Сущность для представления списка покупок в базе данных.
 * Содержит ID списка, ID пользователя, статус и список продуктов.
 */
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingListItem> items;

    // Конструкторы, геттеры и сеттеры

    public ShoppingList() {}

    public ShoppingList(String userId, String status, List<ShoppingListItem> items) {
        this.userId = userId;
        this.status = status;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
    }
}
