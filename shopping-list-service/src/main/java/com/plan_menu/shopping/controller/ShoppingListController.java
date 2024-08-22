package com.plan_menu.shopping.controller;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.exception.ShoppingListNotFoundException;
import com.plan_menu.shopping.service.ShoppingListService;
import com.plan_menu.shopping.mapper.ShoppingListMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления списками покупок.
 * Предоставляет методы для создания, получения, обновления и обработки списков покупок.
 */
@RestController
@RequestMapping("/api/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ShoppingListMapper shoppingListMapper;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingListController.class);

    /**
     * Обрабатывает запрос от планировщика меню для создания списка покупок.
     *
     * @param requestDto DTO с данными списка покупок от планировщика меню.
     * @return ResponseEntity с кодом статуса 200 OK, если обработка прошла успешно.
     */
    @PostMapping("/receive-from-planner")
    public ResponseEntity<Void> receiveFromPlanner(@RequestBody MenuPlannerShoppingListRequestDTO requestDto) {
        try {
            // Преобразование MenuPlannerShoppingListRequestDTO в ShoppingListRequestDTO
            ShoppingListRequestDTO shoppingListRequestDTO = shoppingListMapper.toShoppingListRequestDTO(requestDto);

            // Создание списка покупок
            shoppingListService.createShoppingList(shoppingListRequestDTO);

            logger.info("Received and processed shopping list request from planner with user ID: {}", requestDto.userId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to process shopping list request from planner", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Возвращает список покупок по его идентификатору.
     *
     * @param id идентификатор списка покупок.
     * @return ResponseEntity с найденным списком покупок и кодом статуса 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponseDTO> getShoppingListById(@PathVariable Long id) {
        try {
            ShoppingListResponseDTO shoppingList = shoppingListService.getShoppingListById(id);
            logger.info("Retrieved shopping list with ID: {}", id);
            return ResponseEntity.ok(shoppingList);
        } catch (ShoppingListNotFoundException e) {
            logger.error("Shopping list with ID {} not found", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to retrieve shopping list with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Оптимизирует список покупок по его идентификатору.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @return ResponseEntity с оптимизированным списком покупок и кодом статуса 200 OK.
     */
    @PostMapping("/{shoppingListId}/optimize")
    public ResponseEntity<ShoppingListResponseDTO> optimizeShoppingList(@PathVariable Long shoppingListId) {
        try {
            ShoppingListResponseDTO optimizedList = shoppingListService.optimizeShoppingList(shoppingListId);
            logger.info("Optimized shopping list with ID: {}", shoppingListId);
            return ResponseEntity.ok(optimizedList);
        } catch (ShoppingListNotFoundException e) {
            logger.error("Shopping list with ID {} not found", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to optimize shopping list with ID: {}", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Инициирует процесс размещения заказа на продукты из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @return ResponseEntity с кодом статуса 200 OK, если запрос успешно обработан.
     */
    @PostMapping("/{shoppingListId}/order")
    public ResponseEntity<Void> initiateProductOrder(@PathVariable Long shoppingListId) {
        try {
            shoppingListService.initiateProductOrder(shoppingListId);
            logger.info("Initiated product order for shopping list with ID: {}", shoppingListId);
            return ResponseEntity.ok().build();
        } catch (ShoppingListNotFoundException e) {
            logger.error("Shopping list with ID {} not found", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to initiate product order for shopping list with ID: {}", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Начинает процесс сборки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @return ResponseEntity с кодом статуса 200 OK, если запрос успешно обработан.
     */
    @PostMapping("/{shoppingListId}/collection")
    public ResponseEntity<Void> startProductCollection(@PathVariable Long shoppingListId) {
        try {
            shoppingListService.startProductCollection(shoppingListId);
            logger.info("Started product collection for shopping list with ID: {}", shoppingListId);
            return ResponseEntity.ok().build();
        } catch (ShoppingListNotFoundException e) {
            logger.error("Shopping list with ID {} not found", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to start product collection for shopping list with ID: {}", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Начинает процесс доставки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок.
     * @return ResponseEntity с кодом статуса 200 OK, если запрос успешно обработан.
     */
    @PostMapping("/{shoppingListId}/delivery")
    public ResponseEntity<Void> startProductDelivery(@PathVariable Long shoppingListId) {
        try {
            shoppingListService.startProductDelivery(shoppingListId);
            logger.info("Started product delivery for shopping list with ID: {}", shoppingListId);
            return ResponseEntity.ok().build();
        } catch (ShoppingListNotFoundException e) {
            logger.error("Shopping list with ID {} not found", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to start product delivery for shopping list with ID: {}", shoppingListId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
