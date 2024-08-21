package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import lombok.Data;
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
    ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link ShoppingListDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListDTO DTO списка покупок.
     * @return Сущность списка покупок.
     */
    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);

    /**
     * Преобразует сущность {@link ShoppingListItem} в {@link ShoppingListItemDTO}.
     *
     * @param shoppingListItem Сущность элемента списка покупок.
     * @return DTO элемента списка покупок.
     */
// Маппинг shoppingListId
    ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem);

    /**
     * Преобразует {@link ShoppingListItemDTO} в сущность {@link ShoppingListItem}.
     *
     * @param shoppingListItemDTO DTO элемента списка покупок.
     * @return Сущность элемента списка покупок.
     */
// Маппинг shoppingListId
    ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO);

    /**
     * Преобразует сущность {@link Product} в {@link ProductDTO}.
     *
     * @param product Сущность продукта.
     * @return DTO продукта.
     */
// Маппинг status
    ProductDTO toProductDTO(Product product);

    /**
     * Преобразует {@link ProductDTO} в сущность {@link Product}.
     *
     * @param productDTO DTO продукта.
     * @return Сущность продукта.
     */
 // Маппинг status
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

    ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link NotificationRequestDTO} в {@link NotificationDTO}.
     *
     * @param notificationRequestDTO DTO запроса на уведомление.
     * @return DTO уведомления.
     */

    NotificationDTO toNotificationDTO(NotificationRequestDTO notificationRequestDTO);

    /**
     * Преобразует {@link NotificationDTO} в {@link NotificationRequestDTO}.
     *
     * @param notificationDTO DTO уведомления.
     * @return DTO запроса на уведомление.
     */

    NotificationRequestDTO toNotificationRequestDTO(NotificationDTO notificationDTO);

    /**
     * Преобразует {@link ShoppingListRequestDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListRequestDTO DTO запроса на создание списка покупок.
     * @return Сущность списка покупок.
     */

    ShoppingList toShoppingList(ShoppingListRequestDTO shoppingListRequestDTO);

    /**
     * Преобразует {@link ShoppingList} в {@link ShoppingListRequestDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO запроса на создание списка покупок.
     */

    ShoppingListRequestDTO toShoppingListRequestDTO(ShoppingList shoppingList);

    /**
     * Преобразует {@link ShoppingListResponseDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListResponseDTO DTO ответа со списком покупок.
     * @return Сущность списка покупок.
     */

    ShoppingList toShoppingList(ShoppingListResponseDTO shoppingListResponseDTO);
}
