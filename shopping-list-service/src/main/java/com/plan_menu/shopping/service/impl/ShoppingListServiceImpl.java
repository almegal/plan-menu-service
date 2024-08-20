package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ShoppingListRequestDTO;
import com.plan_menu.shopping.dto.ShoppingListResponseDTO;
import com.plan_menu.shopping.dto.ShoppingListItemRequestDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.entity.ShoppingList;
import com.plan_menu.shopping.entity.ShoppingListItem;
import com.plan_menu.shopping.repository.ShoppingListRepository;
import com.plan_menu.shopping.repository.ShoppingListItemRepository;
import com.plan_menu.shopping.service.ShoppingListService;
import com.plan_menu.shopping.service.ProductService;
import com.plan_menu.shopping.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса списка покупок.
 * Обрабатывает операции со списками покупок.
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ProductService productService;
    private final NotificationService notificationService;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository,
                                   ShoppingListItemRepository shoppingListItemRepository,
                                   ProductService productService,
                                   NotificationService notificationService) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListItemRepository = shoppingListItemRepository;
        this.productService = productService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public ShoppingListResponseDTO createShoppingList(ShoppingListRequestDTO requestDTO) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(requestDTO.name());
        shoppingList.setDescription(requestDTO.description());
        shoppingList.setCreatedDate(LocalDateTime.now()); // Используем LocalDateTime.now()

        List<ShoppingListItem> items = requestDTO.items().stream().map(itemDTO -> {
            ShoppingListItem item = new ShoppingListItem();
            Product product = new Product();
            product.setId(itemDTO.productId());
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity());
            item.setShoppingList(shoppingList);
            return item;
        }).collect(Collectors.toList());

        shoppingList.setItems(items);

        ShoppingList savedShoppingList = shoppingListRepository.save(shoppingList);

        return new ShoppingListResponseDTO(
                savedShoppingList.getId(),
                savedShoppingList.getName(),
                savedShoppingList.getDescription(),
                savedShoppingList.getCreatedDate(), // Используем LocalDateTime
                savedShoppingList.getStatus(),
                savedShoppingList.getCollectionStatus(),
                savedShoppingList.getReadinessStatus(),
                requestDTO.items().stream()
                        .map(itemDTO -> new ShoppingListItemRequestDTO(itemDTO.productId(), itemDTO.quantity(), null))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingListResponseDTO getShoppingListById(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        List<ShoppingListItemRequestDTO> items = shoppingList.getItems().stream().map(item ->
                        new ShoppingListItemRequestDTO(item.getProduct().getId(), item.getQuantity(), item.getUnit()))
                .collect(Collectors.toList());

        return new ShoppingListResponseDTO(
                shoppingList.getId(),
                shoppingList.getName(),
                shoppingList.getDescription(),
                shoppingList.getCreatedDate(), // Используем LocalDateTime
                shoppingList.getStatus(),
                shoppingList.getCollectionStatus(),
                shoppingList.getReadinessStatus(),
                items
        );
    }

    @Override
    @Transactional
    public ShoppingListResponseDTO optimizeShoppingList(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        List<ShoppingListItem> optimizedItems = shoppingList.getItems().stream()
                .map(item -> {
                    int quantity = item.getQuantity();
                    if (quantity > 1.5) {
                        item.setQuantity((quantity / 1) + (quantity % 1 == 0 ? 0 : 1));
                    }
                    return item;
                }).collect(Collectors.toList());

        shoppingList.setItems(optimizedItems);
        ShoppingList updatedShoppingList = shoppingListRepository.save(shoppingList);

        List<ShoppingListItemRequestDTO> items = updatedShoppingList.getItems().stream().map(item ->
                        new ShoppingListItemRequestDTO(item.getProduct().getId(), item.getQuantity(), item.getUnit()))
                .collect(Collectors.toList());

        return new ShoppingListResponseDTO(
                updatedShoppingList.getId(),
                updatedShoppingList.getName(),
                updatedShoppingList.getDescription(),
                updatedShoppingList.getCreatedDate(), // Используем LocalDateTime
                updatedShoppingList.getStatus(),
                updatedShoppingList.getCollectionStatus(),
                updatedShoppingList.getReadinessStatus(),
                items
        );
    }

    @Override
    @Transactional
    public void initiateProductOrder(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        boolean isAvailable = shoppingList.getItems().stream()
                .allMatch(item -> productService.checkProductAvailability(item.getProduct().getId(), item.getQuantity()).available());

        if (!isAvailable) {
            notifyUser(shoppingList.getUserId(), "Не все товары доступны для заказа");
            throw new RuntimeException("Не все товары доступны для заказа");
        }

        productService.placeOrder(shoppingList);

        notifyUser(shoppingList.getUserId(), "Процесс заказа товаров начат для списка покупок: " + shoppingList.getId());

        System.out.println("Инициация заказа для списка покупок: " + shoppingList.getId());
    }

    @Override
    @Transactional
    public void startProductCollection(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        productService.startCollection(shoppingList);

        notifyUser(shoppingList.getUserId(), "Начат процесс сбора товаров для списка покупок: " + shoppingList.getId());

        System.out.println("Сборка товаров для списка покупок: " + shoppingList.getId());
    }

    @Override
    @Transactional
    public void startProductDelivery(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        productService.startDelivery(shoppingList);

        notifyUser(shoppingList.getUserId(), "Доставка товаров начата для списка покупок: " + shoppingList.getId());

        System.out.println("Доставка товаров для списка покупок: " + shoppingList.getId());
    }

    private void notifyUser(Long userId, String message) {
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(userId, message, "ORDER_STATUS");
        notificationService.sendNotification(notificationRequestDTO);
    }
}
