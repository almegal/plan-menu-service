package com.plan_menu.shopping.dto;

import com.plan_menu.shopping.enums.MeasurementUnit;

/**
 * Объект передачи данных (DTO) для передачи информации о продукте между слоями.
 * Этот DTO используется для передачи информации о продукте, такой как идентификатор, название,
 * вес упаковки, краткое описание, количество на складе и единица измерения.
 */
public record ProductDTO(
        Long id,
        String title,
        Double weightPerPack,
        String descriptionShort,
        Integer countOnStorage,
        MeasurementUnit unit,
        String status) { // Добавлено поле status
}
