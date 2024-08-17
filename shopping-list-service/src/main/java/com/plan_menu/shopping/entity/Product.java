package com.plan_menu.shopping.entity;

import com.plan_menu.shopping.enums.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Сущность, представляющая продукт в базе данных.
 * Содержит информацию о продукте, такую как идентификатор, название,
 * вес упаковки, краткое описание, количество на складе и единица измерения.
 */
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double weightPerPack;
    private String descriptionShort;
    private Integer countOnStorage;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;
}
