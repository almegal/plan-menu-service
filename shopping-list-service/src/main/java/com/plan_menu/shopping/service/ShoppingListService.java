package com.plan_menu.shopping.service;

import com.plan_menu.shopping.dto.ShoppingListRequestDTO;
import com.plan_menu.shopping.dto.ShoppingListResponseDTO;

/**
 * Интерфейс для сервиса списка покупок.
 * Предоставляет методы для управления списками покупок.
 */
public interface ShoppingListService {

    /**
     * Создает новый список покупок.
     *
     * @param requestDTO DTO с данными для создания списка покупок
     * @return DTO с информацией о созданном списке покупок
     */
    ShoppingListResponseDTO createShoppingList(ShoppingListRequestDTO requestDTO);

    /**
     * Получает список покупок по его идентификатору.
     *
     * @param id идентификатор списка покупок
     * @return DTO с информацией о списке покупок
     * @throws RuntimeException если список покупок не найден
     */
    ShoppingListResponseDTO getShoppingListById(Long id);

    /**
     * Оптимизирует список покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return DTO с информацией об оптимизированном списке покупок
     * @throws RuntimeException если список покупок не найден
     */
    ShoppingListResponseDTO optimizeShoppingList(Long shoppingListId);

    /**
     * Инициирует заказ продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws RuntimeException если список покупок не найден или не все товары доступны для заказа
     */
    void initiateProductOrder(Long shoppingListId);

    /**
     * Инициирует процесс сборки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws RuntimeException если список покупок не найден
     */
    void startProductCollection(Long shoppingListId);

    /**
     * Инициирует процесс доставки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws RuntimeException если список покупок не найден
     */
    void startProductDelivery(Long shoppingListId);
}
