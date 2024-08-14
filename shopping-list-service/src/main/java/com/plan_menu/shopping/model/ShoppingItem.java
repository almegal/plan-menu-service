package com.plan_menu.shopping.model;

import jakarta.persistence.*;

@Entity
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public ShoppingItem() {}

    public ShoppingItem(String productName, int quantity, String unit, ProductType productType) {
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public ProductType getProductType() {
        return productType;
    }
}

