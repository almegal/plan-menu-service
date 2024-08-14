package com.plan_menu.shopping.dto;

/**
 * DTO для представления продукта в системе.
 * Содержит информацию о продукте, включая его ID, название, объем и вес.
 */
public record ProductDTO(
        Long id,
        String name,
        double volume,
        double weight
) {}
