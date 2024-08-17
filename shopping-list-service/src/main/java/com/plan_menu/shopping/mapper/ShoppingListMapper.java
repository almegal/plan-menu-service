package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Интерфейс маппера для преобразования между сущностями и DTO.
 * Использует MapStruct для генерации реализации маппера.
 */
@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    /**
     * Преобразует ShoppingList в ShoppingListDTO.
     *
     * @param shoppingList сущность ShoppingList
     * @return DTO ShoppingListDTO
     */
    ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList);

    /**
     * Преобразует ShoppingListDTO в ShoppingList.
     *
     * @param shoppingListDTO DTO ShoppingListDTO
     * @return сущность ShoppingList
     */
    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);

    /**
     * Преобразует ShoppingListItem в ShoppingListItemRequestDTO.
     *
     * @param shoppingListItem сущность ShoppingListItem
     * @return DTO ShoppingListItemRequestDTO
     */
    ShoppingListItemRequestDTO toShoppingListItemRequestDTO(ShoppingListItem shoppingListItem);

    /**
     * Преобразует ShoppingListItemRequestDTO в ShoppingListItem.
     *
     * @param shoppingListItemRequestDTO DTO ShoppingListItemRequestDTO
     * @return сущность ShoppingListItem
     */
    ShoppingListItem toShoppingListItem(ShoppingListItemRequestDTO shoppingListItemRequestDTO);

    /**
     * Преобразует Product в ProductDTO.
     *
     * @param product сущность Product
     * @return DTO ProductDTO
     */
    ProductDTO toProductDTO(Product product);

    /**
     * Преобразует ProductDTO в Product.
     *
     * @param productDTO DTO ProductDTO
     * @return сущность Product
     */
    Product toProduct(ProductDTO productDTO);

    /**
     * Преобразует список ShoppingListItem в список ShoppingListItemRequestDTO.
     *
     * @param shoppingListItems список сущностей ShoppingListItem
     * @return список DTO ShoppingListItemRequestDTO
     */
    List<ShoppingListItemRequestDTO> toShoppingListItemRequestDTOs(List<ShoppingListItem> shoppingListItems);

    /**
     * Преобразует список ShoppingListItemRequestDTO в список ShoppingListItem.
     *
     * @param shoppingListItemRequestDTOs список DTO ShoppingListItemRequestDTO
     * @return список сущностей ShoppingListItem
     */
    List<ShoppingListItem> toShoppingListItems(List<ShoppingListItemRequestDTO> shoppingListItemRequestDTOs);

    /**
     * Преобразует ShoppingList в ShoppingListResponseDTO.
     *
     * @param shoppingList сущность ShoppingList
     * @return DTO ShoppingListResponseDTO
     */
    ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList);

    /**
     * Преобразует NotificationRequestDTO в NotificationDTO.
     *
     * @param notificationRequestDTO DTO NotificationRequestDTO
     * @return DTO NotificationDTO
     */
    NotificationDTO toNotificationDTO(NotificationRequestDTO notificationRequestDTO);

    /**
     * Преобразует NotificationDTO в NotificationRequestDTO.
     *
     * @param notificationDTO DTO NotificationDTO
     * @return DTO NotificationRequestDTO
     */
    NotificationRequestDTO toNotificationRequestDTO(NotificationDTO notificationDTO);
}
