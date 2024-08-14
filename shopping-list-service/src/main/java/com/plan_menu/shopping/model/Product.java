package com.plan_menu.shopping.model;

import jakarta.persistence.*;

/**
 * Сущность для представления продукта в базе данных.
 * Содержит ID продукта, его название, объем, вес и тип.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double volume;
    private double weight;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    // Конструкторы, геттеры и сеттеры

    public Product() {}

    public Product(String name, double volume, double weight, ProductType productType) {
        this.name = name;
        this.volume = volume;
        this.weight = weight;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
