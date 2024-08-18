package com.plan_menu.shopping.service;

import com.plan_menu.shopping.dto.ShoppingListRequestDTO;
import com.plan_menu.shopping.dto.ShoppingListResponseDTO;

/**
 * Интерфейс для сервиса списков покупок.
 * Предоставляет методы для управления списками покупок.
 */
public interface ShoppingListService {

    /**
     * Создает новый список покупок.
     *
     * @param requestDTO данные для создания списка покупок
     * @return DTO с информацией о созданном списке покупок
     */
    ShoppingListResponseDTO createShoppingList(ShoppingListRequestDTO requestDTO);

    /**
     * Возвращает список покупок по его идентификатору.
     *
     * @param id идентификатор списка покупок
     * @return DTO с информацией о списке покупок
     */
    ShoppingListResponseDTO getShoppingListById(Long id);

    /**
     * Оптимизирует список покупок для пользователя.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return DTO с оптимизированным списком покупок
     */
    ShoppingListResponseDTO optimizeShoppingList(Long shoppingListId);

    /**
     * Инициирует процесс заказа товаров из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     */
    void initiateProductOrder(Long shoppingListId);

    /**
     * Запускает процесс сборки товаров для доставки.
     *
     * @param shoppingListId идентификатор списка покупок
     */
    void startProductCollection(Long shoppingListId);

    /**
     * Запускает процесс доставки товаров.
     *
     * @param shoppingListId идентификатор списка покупок
     */
    void startProductDelivery(Long shoppingListId);
}
