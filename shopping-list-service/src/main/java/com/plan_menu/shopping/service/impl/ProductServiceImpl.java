package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ProductAvailabilityResponseDTO;
import com.plan_menu.shopping.dto.ProductDTO;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.entity.ShoppingList;
import com.plan_menu.shopping.exception.ProductNotFoundException;
import com.plan_menu.shopping.mapper.ShoppingListMapper;
import com.plan_menu.shopping.repository.ProductRepository;
import com.plan_menu.shopping.service.NotificationService;
import com.plan_menu.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса продуктов.
 * Обрабатывает операции с продуктами, такие как проверка доступности и поиск продуктов.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ShoppingListMapper shoppingListMapper;
    private final NotificationService notificationService;

    @Override
    public ProductAvailabilityResponseDTO checkProductAvailability(Long productId, int count) {
        boolean isAvailable = productRepository.isProductAvailable(productId, count);
        return new ProductAvailabilityResponseDTO(productId, isAvailable, null);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        return shoppingListMapper.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> searchProductsByTitle(String titlePart) {
        List<Product> products = productRepository.findByTitleContaining(titlePart);
        return products.stream()
                .map(shoppingListMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void placeOrder(ShoppingList shoppingList) {
        System.out.println("Заказ продуктов из списка покупок: " + shoppingList.getId());
    }

    @Override
    public void startCollection(ShoppingList shoppingList) {
        System.out.println("Начало сборки продуктов из списка покупок: " + shoppingList.getId());

        // Обрабатываем каждый элемент в списке покупок
        shoppingList.getItems().forEach(item -> {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            // Проверяем доступность продукта
            if (!productRepository.isProductAvailable(product.getId(), quantity)) {
                throw new RuntimeException("Продукт " + product.getId() + " не доступен в нужном количестве");
            }

            // Устанавливаем статус продукта
            product.setStatus("IN_COLLECTION");
            productRepository.save(product);
        });

        // Отправляем уведомление сотрудникам
        notificationService.sendNotificationToStaff("Начало сборки продуктов для списка покупок: " + shoppingList.getId());
    }

    @Override
    public void startDelivery(ShoppingList shoppingList) {
        System.out.println("Начало доставки продуктов из списка покупок: " + shoppingList.getId());

        // Проверяем, что все продукты собраны
        boolean allProductsCollected = shoppingList.getItems().stream()
                .allMatch(item -> "COLLECTED".equals(item.getProduct().getStatus()));

        if (!allProductsCollected) {
            throw new RuntimeException("Не все продукты собраны для списка покупок: " + shoppingList.getId());
        }

        // Устанавливаем статус для всех продуктов
        shoppingList.getItems().forEach(item -> {
            Product product = item.getProduct();
            product.setStatus("IN_DELIVERY");
            productRepository.save(product);
        });

        // Отправляем уведомление пользователю
        notificationService.sendNotificationToUser(shoppingList.getUserId(), "Доставка продуктов для списка покупок " + shoppingList.getId() + " начата");
    }
}
