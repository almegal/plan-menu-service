package com.plan_menu.shopping.repository;

import com.plan_menu.shopping.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с элементами списка покупок.
 * Предоставляет методы для выполнения CRUD операций к таблице элементов списка покупок.
 */
@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {

    /**
     * Находит элементы списка покупок по идентификатору списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @return список элементов списка покупок, связанных с указанным списком.
     */
    List<ShoppingListItem> findByShoppingListId(Long shoppingListId);

    /**
     * Находит элементы списка покупок по идентификатору продукта.
     *
     * @param productId идентификатор продукта.
     * @return список элементов списка покупок, содержащих указанный продукт.
     */
    List<ShoppingListItem> findByProductId(Long productId);

    /**
     * Находит элементы списка покупок по идентификатору списка покупок и идентификатору продукта.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @param productId идентификатор продукта.
     * @return элемент списка покупок, соответствующий указанному списку и продукту, если он существует.
     */
    @Query("SELECT s FROM ShoppingListItem s WHERE s.shoppingList.id = :shoppingListId AND s.product.id = :productId")
    ShoppingListItem findByShoppingListIdAndProductId(Long shoppingListId, Long productId);

    /**
     * Удаляет элементы списка покупок по идентификатору списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     */
    void deleteByShoppingListId(Long shoppingListId);

    /**
     * Удаляет элементы списка покупок по идентификатору продукта.
     *
     * @param productId идентификатор продукта.
     */
    void deleteByProductId(Long productId);

    /**
     * Проверяет, существует ли элемент списка покупок с указанным идентификатором продукта и списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @param productId идентификатор продукта.
     * @return true, если элемент существует, иначе false.
     */
    boolean existsByShoppingListIdAndProductId(Long shoppingListId, Long productId);
}
