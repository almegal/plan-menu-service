package com.plan_menu.shopping.controller;

import com.plan_menu.shopping.dto.ProductAvailabilityResponseDTO;
import com.plan_menu.shopping.dto.ProductDTO;
import com.plan_menu.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления продуктами.
 * Предоставляет методы для проверки доступности продуктов, получения информации о продукте и поиска продуктов.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Проверяет доступность продукта на складе.
     *
     * @param productId идентификатор продукта
     * @param count количество продукта
     * @return ResponseEntity с результатом доступности продукта
     */
    @GetMapping("/availability")
    public ResponseEntity<ProductAvailabilityResponseDTO> checkProductAvailability(@RequestParam Long productId,
                                                                                   @RequestParam int count) {
        ProductAvailabilityResponseDTO availability = productService.checkProductAvailability(productId, count);
        return ResponseEntity.ok(availability);
    }

    /**
     * Получает информацию о продукте по его идентификатору.
     *
     * @param productId идентификатор продукта
     * @return ResponseEntity с информацией о продукте
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    /**
     * Ищет продукты по части названия.
     *
     * @param titlePart часть названия продукта
     * @return ResponseEntity со списком продуктов, соответствующих поисковому запросу
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProductsByTitle(@RequestParam String titlePart) {
        List<ProductDTO> products = productService.searchProductsByTitle(titlePart);
        return ResponseEntity.ok(products);
    }
}
