package com.plan_menu.shopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Сущность для представления отдельного продукта в списке покупок в базе данных.
 * Содержит ID продукта, его название, фактически предоставленный объем и вес.
 */
@Entity
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private double providedVolume;
    private double providedWeight;

    // Конструкторы, геттеры и сеттеры

    public ShoppingListItem() {}

    public ShoppingListItem(String productName, double providedVolume, double providedWeight) {
        this.productName = productName;
        this.providedVolume = providedVolume;
        this.providedWeight = providedWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProvidedVolume() {
        return providedVolume;
    }

    public void setProvidedVolume(double providedVolume) {
        this.providedVolume = providedVolume;
    }

    public double getProvidedWeight() {
        return providedWeight;
    }

    public void setProvidedWeight(double providedWeight) {
        this.providedWeight = providedWeight;
    }
}
