package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ShoppingListRequestDTO;
import com.plan_menu.shopping.dto.ShoppingListResponseDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.entity.ShoppingList;
import com.plan_menu.shopping.entity.ShoppingListItem;
import com.plan_menu.shopping.exception.ShoppingListNotFoundException;
import com.plan_menu.shopping.feign.MenuPlannerClient;
import com.plan_menu.shopping.feign.NotificationClient;
import com.plan_menu.shopping.mapper.ShoppingListMapper;
import com.plan_menu.shopping.repository.ProductRepository;
import com.plan_menu.shopping.repository.ShoppingListRepository;
import com.plan_menu.shopping.service.NotificationService;
import com.plan_menu.shopping.service.ProductService;
import com.plan_menu.shopping.service.ShoppingListService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса списка покупок.
 * Обрабатывает операции со списками покупок.
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final NotificationService notificationService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final MenuPlannerClient menuPlannerClient;
    private final NotificationClient notificationClient;
    private final ShoppingListMapper shoppingListMapper;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param shoppingListRepository репозиторий для работы со списками покупок
     * @param notificationService сервис уведомлений
     * @param productService сервис продуктов
     * @param productRepository репозиторий для работы с продуктами
     * @param menuPlannerClient клиент для взаимодействия с Meal Planning Service
     * @param notificationClient клиент для отправки уведомлений
     * @param shoppingListMapper маппер для преобразования между сущностями и DTO
     */
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository,
                                   NotificationService notificationService,
                                   ProductService productService,
                                   ProductRepository productRepository,
                                   MenuPlannerClient menuPlannerClient,
                                   NotificationClient notificationClient,
                                   ShoppingListMapper shoppingListMapper) {
        this.shoppingListRepository = shoppingListRepository;
        this.notificationService = notificationService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.menuPlannerClient = menuPlannerClient;
        this.notificationClient = notificationClient;
        this.shoppingListMapper = shoppingListMapper;
    }

    @Override
    public ShoppingListResponseDTO createShoppingList(ShoppingListRequestDTO requestDTO) {
        // Получение данных от Meal Planning Service
        var mealPlan = menuPlannerClient.getMealPlanById(requestDTO.mealPlanId());

        // Создание списка покупок на основе mealPlan
        var shoppingList = shoppingListMapper.toShoppingList(requestDTO);
        shoppingList.setCreatedDate(LocalDateTime.now());
        shoppingList = shoppingListRepository.save(shoppingList);

        // Отправка уведомления пользователю
        var notificationRequestDTO = new NotificationRequestDTO(
                requestDTO.userId(),
                "Ваш список покупок создан",
                "INFO"
        );
        notificationClient.sendNotification(notificationRequestDTO);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    @Override
    public ShoppingListResponseDTO getShoppingListById(Long id) {
        var shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + id));
        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    @Override
    public ShoppingListResponseDTO optimizeShoppingList(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        for (var item : shoppingList.getItems()) {
            int requiredQuantity = item.getQuantity();
            Long productId = item.getProduct().getId();

            // Получаем список доступных упаковок для данного продукта
            var availableProducts = productRepository.findAvailableProducts(productId);

            // Оптимизируем количество с использованием метода оптимального подбора
            var optimizedProducts = findOptimalCombination(availableProducts, requiredQuantity);

            // Обновляем список покупок
            item.setOptimizedProducts(optimizedProducts);
        }

        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    @Override
    public void initiateProductOrder(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("ORDER_INITIATED");
        shoppingList = shoppingListRepository.save(shoppingList);

        productService.placeOrder(shoppingList);
    }

    @Override
    public void startProductCollection(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("COLLECTION_STARTED");
        shoppingListRepository.save(shoppingList);

        productService.startCollection(shoppingList);
    }

    @Override
    public void startProductDelivery(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("DELIVERY_STARTED");
        shoppingListRepository.save(shoppingList);

        productService.startDelivery(shoppingList);
    }

    @Scheduled(fixedDelay = 15000) // Задержка в 15 секунд
    public void processOrder() {
        var shoppingListOpt = shoppingListRepository.findFirstByStatus("NEW");
        shoppingListOpt.ifPresent(shoppingList -> {
            Long shoppingListId = shoppingList.getId();
            Long userId = shoppingList.getUserId();

            // Имитация процесса сборки заказа
            initiateProductOrder(shoppingListId);
            notificationService.sendNotificationToUser(userId, "Заказ принят");

            try {
                Thread.sleep(15000); // Задержка в 15 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startProductCollection(shoppingListId);
            notificationService.sendNotificationToUser(userId, "Сборка заказа начата");

            try {
                Thread.sleep(15000); // Задержка в 15 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            notificationService.sendNotificationToUser(userId, "Ожидание курьера");

            try {
                Thread.sleep(15000); // Задержка в 15 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startProductDelivery(shoppingListId);
            notificationService.sendNotificationToUser(userId, "Передано курьеру");

            // Имитация завершения процесса сборки заказа
            shoppingList.setStatus("DELIVERED");
            shoppingListRepository.save(shoppingList);
        });
    }

    private List<Product> findOptimalCombination(List<Product> availableProducts, int requiredQuantity) {
        List<Product> selectedProducts = new ArrayList<>();
        int remainingQuantity = requiredQuantity;

        availableProducts.sort((p1, p2) -> Integer.compare(p2.getVolumeOrWeight(), p1.getVolumeOrWeight())); // Сортируем от большего к меньшему

        for (var product : availableProducts) {
            if (remainingQuantity <= 0) {
                break;
            }
            int productVolumeOrWeight = product.getVolumeOrWeight();
            if (productVolumeOrWeight <= remainingQuantity) {
                selectedProducts.add(product);
                remainingQuantity -= productVolumeOrWeight;
            }
        }

        // Проверяем, есть ли остаток и можем ли его как-то покрыть
        if (remainingQuantity > 0) {
            for (var product : availableProducts) {
                int productVolumeOrWeight = product.getVolumeOrWeight();
                if (productVolumeOrWeight >= remainingQuantity) {
                    selectedProducts.add(product);
                    remainingQuantity -= productVolumeOrWeight;
                    break;
                }
            }
        }

        return selectedProducts;
    }
}
