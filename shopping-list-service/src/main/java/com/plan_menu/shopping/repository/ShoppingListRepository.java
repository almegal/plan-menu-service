package com.plan_menu.shopping.repository;

import com.plan_menu.shopping.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы со списками покупок.
 * Предоставляет методы для выполнения CRUD операций и кастомных запросов к таблице списков покупок.
 */
@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    /**
     * Находит списки покупок по идентификатору пользователя.
     *
     * @param userId идентификатор пользователя.
     * @return список списков покупок, принадлежащих пользователю.
     */
    List<ShoppingList> findByUserId(Long userId);

    /**
     * Находит списки покупок по части названия.
     *
     * @param namePart часть названия списка покупок.
     * @return список списков покупок, содержащих указанную часть названия.
     */
    @Query("SELECT s FROM ShoppingList s WHERE s.name LIKE %:namePart%")
    List<ShoppingList> findByNameContaining(String namePart);
}
