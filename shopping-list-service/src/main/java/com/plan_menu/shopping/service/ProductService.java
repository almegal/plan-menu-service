package com.plan_menu.shopping.service;

import com.plan_menu.shopping.dto.ProductAvailabilityResponseDTO;
import com.plan_menu.shopping.dto.ProductDTO;
import com.plan_menu.shopping.entity.ShoppingList;
import java.util.List;

/**
 * Интерфейс для сервиса продуктов.
 * Предоставляет методы для управления продуктами, включая проверку доступности и поиск продуктов.
 */
public interface ProductService {

    /**
     * Проверяет доступность продукта на складе.
     *
     * @param productId идентификатор продукта
     * @param count количество продукта
     * @return DTO с информацией о доступности продукта
     */
    ProductAvailabilityResponseDTO checkProductAvailability(Long productId, int count);

    /**
     * Получает информацию о продукте по его идентификатору.
     *
     * @param productId идентификатор продукта
     * @return DTO с информацией о продукте
     * @throws com.plan_menu.shopping.exception.ProductNotFoundException если продукт не найден
     */
    ProductDTO getProductById(Long productId);

    /**
     * Ищет продукты по части названия.
     *
     * @param titlePart часть названия продукта
     * @return список DTO с информацией о найденных продуктах
     */
    List<ProductDTO> searchProductsByTitle(String titlePart);

    /**
     * Отправляет запрос на заказ продуктов из списка покупок.
     *
     * @param shoppingList список покупок
     */
    void placeOrder(ShoppingList shoppingList);

    /**
     * Инициирует процесс сборки продуктов из списка покупок.
     *
     * @param shoppingList список покупок
     */
    void startCollection(ShoppingList shoppingList);

    /**
     * Инициирует процесс доставки продуктов из списка покупок.
     *
     * @param shoppingList список покупок
     */
    void startDelivery(ShoppingList shoppingList);
}
