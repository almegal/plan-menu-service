package com.plan_menu.shopping.controller;

import com.plan_menu.shopping.dto.ShoppingListRequestDTO;
import com.plan_menu.shopping.dto.ShoppingListResponseDTO;
import com.plan_menu.shopping.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления списками покупок.
 * Предоставляет методы для создания, получения, обновления и удаления списков покупок.
 */
@RestController
@RequestMapping("/api/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    /**
     * Создает новый список покупок.
     *
     * @param requestDTO данные для создания списка покупок
     * @return ResponseEntity с созданным списком покупок
     */
    @PostMapping
    public ResponseEntity<ShoppingListResponseDTO> createShoppingList(@RequestBody ShoppingListRequestDTO requestDTO) {
        ShoppingListResponseDTO shoppingList = shoppingListService.createShoppingList(requestDTO);
        return ResponseEntity.ok(shoppingList);
    }

    /**
     * Возвращает список покупок по его идентификатору.
     *
     * @param id идентификатор списка покупок
     * @return ResponseEntity с найденным списком покупок
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponseDTO> getShoppingListById(@PathVariable Long id) {
        ShoppingListResponseDTO shoppingList = shoppingListService.getShoppingListById(id);
        return ResponseEntity.ok(shoppingList);
    }

    /**
     * Оптимизирует список покупок для пользователя.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return ResponseEntity с оптимизированным списком покупок
     */
    @PostMapping("/{shoppingListId}/optimize")
    public ResponseEntity<ShoppingListResponseDTO> optimizeShoppingList(@PathVariable Long shoppingListId) {
        try {
            ShoppingListResponseDTO optimizedList = shoppingListService.optimizeShoppingList(shoppingListId);
            return ResponseEntity.ok(optimizedList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Инициирует процесс заказа товаров из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return ResponseEntity с результатом выполнения заказа
     */
    @PostMapping("/{shoppingListId}/order")
    public ResponseEntity<String> initiateProductOrder(@PathVariable Long shoppingListId) {
        shoppingListService.initiateProductOrder(shoppingListId);
        return ResponseEntity.ok("Order initiated successfully.");
    }

    /**
     * Запускает процесс сборки товаров для доставки.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return ResponseEntity с результатом выполнения сборки товаров
     */
    @PostMapping("/{shoppingListId}/collect")
    public ResponseEntity<String> startProductCollection(@PathVariable Long shoppingListId) {
        shoppingListService.startProductCollection(shoppingListId);
        return ResponseEntity.ok("Product collection started successfully.");
    }

    /**
     * Запускает процесс доставки товаров.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return ResponseEntity с результатом выполнения доставки товаров
     */
    @PostMapping("/{shoppingListId}/deliver")
    public ResponseEntity<String> startProductDelivery(@PathVariable Long shoppingListId) {
        shoppingListService.startProductDelivery(shoppingListId);
        return ResponseEntity.ok("Product delivery started successfully.");
    }
}
