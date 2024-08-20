package com.plan_menu.shopping.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность, представляющая список покупок.
 * Содержит информацию о списке покупок, такую как идентификатор, название, описание,
 * дата создания, статус, статус сборки, статус готовности, идентификатор пользователя и список элементов.
 */
@Data
@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    private String status;

    @Column(name = "collection_status")
    private String collectionStatus;

    @Column(name = "readiness_status")
    private String readinessStatus;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingListItem> items;
}
