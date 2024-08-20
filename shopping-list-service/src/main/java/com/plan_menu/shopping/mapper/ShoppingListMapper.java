package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Маппер для преобразования между сущностями и DTO в контексте списка покупок.
 */
@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    /**
     * Преобразует сущность {@link ShoppingList} в {@link ShoppingListDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO списка покупок.
     */
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "collectionStatus", target = "collectionStatus")
    @Mapping(source = "readinessStatus", target = "readinessStatus")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "items", target = "items")
    ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link ShoppingListDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListDTO DTO списка покупок.
     * @return Сущность списка покупок.
     */
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "collectionStatus", target = "collectionStatus")
    @Mapping(source = "readinessStatus", target = "readinessStatus")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "items", target = "items")
    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);

    /**
     * Преобразует сущность {@link ShoppingListItem} в {@link ShoppingListItemDTO}.
     *
     * @param shoppingListItem Сущность элемента списка покупок.
     * @return DTO элемента списка покупок.
     */
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "unit", target = "unit")
    @Mapping(source = "shoppingList.id", target = "shoppingListId") // Маппинг shoppingListId
    ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem);

    /**
     * Преобразует {@link ShoppingListItemDTO} в сущность {@link ShoppingListItem}.
     *
     * @param shoppingListItemDTO DTO элемента списка покупок.
     * @return Сущность элемента списка покупок.
     */
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "unit", target = "unit")
    @Mapping(source = "shoppingListId", target = "shoppingList.id") // Маппинг shoppingListId
    ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO);

    /**
     * Преобразует сущность {@link Product} в {@link ProductDTO}.
     *
     * @param product Сущность продукта.
     * @return DTO продукта.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "weightPerPack", target = "weightPerPack")
    @Mapping(source = "descriptionShort", target = "descriptionShort")
    @Mapping(source = "countOnStorage", target = "countOnStorage")
    @Mapping(source = "unit", target = "unit")
    @Mapping(source = "status", target = "status") // Маппинг status
    ProductDTO toProductDTO(Product product);

    /**
     * Преобразует {@link ProductDTO} в сущность {@link Product}.
     *
     * @param productDTO DTO продукта.
     * @return Сущность продукта.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "weightPerPack", target = "weightPerPack")
    @Mapping(source = "descriptionShort", target = "descriptionShort")
    @Mapping(source = "countOnStorage", target = "countOnStorage")
    @Mapping(source = "unit", target = "unit")
    @Mapping(source = "status", target = "status") // Маппинг status
    Product toProduct(ProductDTO productDTO);

    /**
     * Преобразует список сущностей {@link ShoppingListItem} в список {@link ShoppingListItemDTO}.
     *
     * @param shoppingListItems Список сущностей элементов списка покупок.
     * @return Список DTO элементов списка покупок.
     */
    List<ShoppingListItemDTO> toShoppingListItemDTOs(List<ShoppingListItem> shoppingListItems);

    /**
     * Преобразует список {@link ShoppingListItemDTO} в список сущностей {@link ShoppingListItem}.
     *
     * @param shoppingListItemDTOs Список DTO элементов списка покупок.
     * @return Список сущностей элементов списка покупок.
     */
    List<ShoppingListItem> toShoppingListItems(List<ShoppingListItemDTO> shoppingListItemDTOs);

    /**
     * Преобразует сущность {@link ShoppingList} в {@link ShoppingListResponseDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO ответа со списком покупок.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "collectionStatus", target = "collectionStatus")
    @Mapping(source = "readinessStatus", target = "readinessStatus")
    @Mapping(source = "items", target = "items")
    ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link NotificationRequestDTO} в {@link NotificationDTO}.
     *
     * @param notificationRequestDTO DTO запроса на уведомление.
     * @return DTO уведомления.
     */
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "message", target = "message")
    NotificationDTO toNotificationDTO(NotificationRequestDTO notificationRequestDTO);

    /**
     * Преобразует {@link NotificationDTO} в {@link NotificationRequestDTO}.
     *
     * @param notificationDTO DTO уведомления.
     * @return DTO запроса на уведомление.
     */
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "message", target = "message")
    NotificationRequestDTO toNotificationRequestDTO(NotificationDTO notificationDTO);

    /**
     * Преобразует {@link ShoppingListRequestDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListRequestDTO DTO запроса на создание списка покупок.
     * @return Сущность списка покупок.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "mealPlanId", target = "mealPlanId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "items", target = "items")
    ShoppingList toShoppingList(ShoppingListRequestDTO shoppingListRequestDTO);

    /**
     * Преобразует {@link ShoppingList} в {@link ShoppingListRequestDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO запроса на создание списка покупок.
     */
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "items", target = "items")
    ShoppingListRequestDTO toShoppingListRequestDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link ShoppingListResponseDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListResponseDTO DTO ответа со списком покупок.
     * @return Сущность списка покупок.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "collectionStatus", target = "collectionStatus")
    @Mapping(source = "readinessStatus", target = "readinessStatus")
    @Mapping(source = "items", target = "items")
    ShoppingList toShoppingList(ShoppingListResponseDTO shoppingListResponseDTO);
}
