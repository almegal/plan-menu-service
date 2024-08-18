package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ProductAvailabilityResponseDTO;
import com.plan_menu.shopping.dto.ProductDTO;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.exception.ProductNotFoundException;
import com.plan_menu.shopping.mapper.ShoppingListMapper;
import com.plan_menu.shopping.repository.ProductRepository;
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

    /**
     * Проверяет доступность продукта на складе.
     *
     * @param productId идентификатор продукта
     * @param count количество продукта
     * @return DTO с информацией о доступности продукта
     */
    @Override
    public ProductAvailabilityResponseDTO checkProductAvailability(Long productId, int count) {
        boolean isAvailable = productRepository.isProductAvailable(productId, count);
        return new ProductAvailabilityResponseDTO(productId, isAvailable, null);
    }

    /**
     * Получает информацию о продукте по его идентификатору.
     *
     * @param productId идентификатор продукта
     * @return DTO с информацией о продукте
     * @throws ProductNotFoundException если продукт не найден
     */
    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        return shoppingListMapper.toProductDTO(product);
    }

    /**
     * Ищет продукты по части названия.
     *
     * @param titlePart часть названия продукта
     * @return список DTO с информацией о найденных продуктах
     */
    @Override
    public List<ProductDTO> searchProductsByTitle(String titlePart) {
        List<Product> products = productRepository.findByTitleContaining(titlePart);
        return products.stream()
                .map(shoppingListMapper::toProductDTO)
                .collect(Collectors.toList());
    }
}
