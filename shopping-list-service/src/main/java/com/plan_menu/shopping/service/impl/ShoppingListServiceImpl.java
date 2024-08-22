package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.exception.ShoppingListNotFoundException;
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

/**
 * Реализация сервиса списка покупок.
 * Обрабатывает операции со списками покупок, включая создание, оптимизацию, сборку и доставку.
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final NotificationService notificationService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ShoppingListMapper shoppingListMapper;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param shoppingListRepository репозиторий для работы со списками покупок
     * @param notificationService сервис уведомлений
     * @param productService сервис продуктов
     * @param productRepository репозиторий для работы с продуктами
     * @param shoppingListMapper маппер для преобразования между сущностями и DTO
     */
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository,
                                   NotificationService notificationService,
                                   ProductService productService,
                                   ProductRepository productRepository,
                                   ShoppingListMapper shoppingListMapper) {
        this.shoppingListRepository = shoppingListRepository;
        this.notificationService = notificationService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.shoppingListMapper = shoppingListMapper;
    }

    /**
     * Создает новый список покупок на основе данных из DTO.
     *
     * @param requestDTO DTO с данными для создания списка покупок
     * @return DTO с данными созданного списка покупок
     */
    @Override
    public ShoppingListResponseDTO createShoppingList(ShoppingListRequestDTO requestDTO) {
        // Создание списка покупок на основе данных из DTO
        var shoppingList = shoppingListMapper.toShoppingList(requestDTO);
        shoppingList.setCreatedDate(LocalDateTime.now());
        shoppingList = shoppingListRepository.save(shoppingList);

        // Отправка уведомления пользователю
        var notificationRequestDTO = new NotificationRequestDTO(
                requestDTO.userId(),
                "Ваш список покупок создан",
                "INFO"
        );
        notificationService.sendNotification(notificationRequestDTO);

        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    /**
     * Получает список покупок по его идентификатору.
     *
     * @param id идентификатор списка покупок
     * @return DTO с данными списка покупок
     * @throws ShoppingListNotFoundException если список покупок не найден
     */
    @Override
    public ShoppingListResponseDTO getShoppingListById(Long id) {
        var shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + id));
        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    /**
     * Оптимизирует список покупок для использования упаковок продуктов.
     *
     * @param shoppingListId идентификатор списка покупок
     * @return DTO с оптимизированным списком покупок
     * @throws ShoppingListNotFoundException если список покупок не найден
     */
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
            var optimizedProducts = findOptimalCombination(availableProducts, requiredQuantity, shoppingList.getUserId());

            // Обновляем список покупок
            item.setOptimizedProducts(optimizedProducts);
        }

        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.toShoppingListResponseDTO(shoppingList);
    }

    /**
     * Инициирует процесс размещения заказа на продукты из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws ShoppingListNotFoundException если список покупок не найден
     */
    @Override
    public void initiateProductOrder(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("ORDER_INITIATED");
        shoppingList = shoppingListRepository.save(shoppingList);

        productService.placeOrder(shoppingList);
    }

    /**
     * Начинает процесс сборки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws ShoppingListNotFoundException если список покупок не найден
     */
    @Override
    public void startProductCollection(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("COLLECTION_STARTED");
        shoppingListRepository.save(shoppingList);

        productService.startCollection(shoppingList);
    }

    /**
     * Начинает процесс доставки продуктов из списка покупок.
     *
     * @param shoppingListId идентификатор списка покупок
     * @throws ShoppingListNotFoundException если список покупок не найден
     */
    @Override
    public void startProductDelivery(Long shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException("Shopping List not found with id: " + shoppingListId));

        shoppingList.setStatus("DELIVERY_STARTED");
        shoppingListRepository.save(shoppingList);

        productService.startDelivery(shoppingList);
    }

    /**
     * Планировщик для обработки заказов с имитацией задержек.
     * Обрабатывает заказы по этапам: размещение, сборка, доставка.
     */
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

    /**
     * Находит оптимальную комбинацию упаковок для удовлетворения требуемого количества продукта.
     *
     * @param availableProducts список доступных упаковок продукта
     * @param requiredQuantity требуемое количество продукта
     * @param userId идентификатор пользователя для уведомления
     * @return список оптимальных упаковок
     */
    private List<Product> findOptimalCombination(List<Product> availableProducts, int requiredQuantity, Long userId) {
        List<Product> selectedProducts = new ArrayList<>();
        int remainingQuantity = requiredQuantity;

        availableProducts.sort((p1, p2) -> Integer.compare(p2.getVolumeOrWeight(), p1.getVolumeOrWeight())); // Сортируем от большего к меньшему

        for (Product product : availableProducts) {
            while (remainingQuantity >= product.getVolumeOrWeight()) {
                selectedProducts.add(product);
                remainingQuantity -= product.getVolumeOrWeight();
            }
        }

        if (remainingQuantity > 0) {
            handleRemainingQuantity(remainingQuantity, userId);
        }

        return selectedProducts;
    }

    /**
     * Обрабатывает оставшееся количество продукта, если его не удается полностью удовлетворить.
     *
     * @param remainingQuantity оставшееся количество продукта
     * @param userId идентификатор пользователя для уведомления
     */
    private void handleRemainingQuantity(int remainingQuantity, Long userId) {
        String message = "Не удалось полностью удовлетворить количество продукта. Осталось " + remainingQuantity + " единиц.";
        notificationService.sendNotificationToUser(userId, message);
    }
}
