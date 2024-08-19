package com.plan_menu.shopping.entity;

import com.plan_menu.shopping.enums.MeasurementUnit;
import lombok.Data;
import jakarta.persistence.*;

/**
 * Сущность, представляющая продукт.
 * Содержит информацию о продукте, такую как идентификатор, название, вес упаковки,
 * краткое описание, количество на складе, единица измерения и статус.
 */
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "weight_per_pack")
    private Double weightPerPack;

    @Column(name = "description_short")
    private String descriptionShort;

    @Column(name = "count_on_storage")
    private Integer countOnStorage;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    private String status;

    public int getVolumeOrWeight() {
        return weightPerPack.intValue();
    }
}
