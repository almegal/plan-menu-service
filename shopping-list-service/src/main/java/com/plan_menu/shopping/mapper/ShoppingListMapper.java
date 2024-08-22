/*
package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

*/
/**
 * Маппер для преобразования между сущностями и DTO в контексте списка покупок.
 *//*

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    */
/**
     * Преобразует сущность {@link ShoppingList} в {@link ShoppingListDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO списка покупок.
     *//*

    ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList);

    */
/**
     * Преобразует {@link ShoppingListDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListDTO DTO списка покупок.
     * @return Сущность списка покупок.
     *//*

    ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO);

    */
/**
     * Преобразует сущность {@link ShoppingListItem} в {@link ShoppingListItemDTO}.
     *
     * @param shoppingListItem Сущность элемента списка покупок.
     * @return DTO элемента списка покупок.
     *//*

    @Mapping(target = "shoppingListId", source = "shoppingList.id")
    ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem);

    */
/**
     * Преобразует {@link ShoppingListItemDTO} в сущность {@link ShoppingListItem}.
     *
     * @param shoppingListItemDTO DTO элемента списка покупок.
     * @return Сущность элемента списка покупок.
     *//*

    @Mapping(target = "shoppingList.id", source = "shoppingListId")
    ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO);

    */
/**
     * Преобразует сущность {@link Product} в {@link ProductDTO}.
     *
     * @param product Сущность продукта.
     * @return DTO продукта.
     *//*

    @Mapping(target = "status", source = "status")
    ProductDTO toProductDTO(Product product);

    */
/**
     * Преобразует {@link ProductDTO} в сущность {@link Product}.
     *
     * @param productDTO DTO продукта.
     * @return Сущность продукта.
     *//*

    @Mapping(target = "status", source = "status")
    Product toProduct(ProductDTO productDTO);

    */
/**
     * Преобразует список сущностей {@link ShoppingListItem} в список {@link ShoppingListItemDTO}.
     *
     * @param shoppingListItems Список сущностей элементов списка покупок.
     * @return Список DTO элементов списка покупок.
     *//*

    List<ShoppingListItemDTO> toShoppingListItemDTOs(List<ShoppingListItem> shoppingListItems);

    */
/**
     * Преобразует список {@link ShoppingListItemDTO} в список сущностей {@link ShoppingListItem}.
     *
     * @param shoppingListItemDTOs Список DTO элементов списка покупок.
     * @return Список сущностей элементов списка покупок.
     *//*

    List<ShoppingListItem> toShoppingListItems(List<ShoppingListItemDTO> shoppingListItemDTOs);

    */
/**
     * Преобразует сущность {@link ShoppingList} в {@link ShoppingListResponseDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO ответа со списком покупок.
     *//*

    ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList);

    */
/**
     * Преобразует {@link NotificationRequestDTO} в {@link NotificationDTO}.
     *
     * @param notificationRequestDTO DTO запроса на уведомление.
     * @return DTO уведомления.
     *//*

    NotificationDTO toNotificationDTO(NotificationRequestDTO notificationRequestDTO);

    */
/**
     * Преобразует {@link NotificationDTO} в {@link NotificationRequestDTO}.
     *
     * @param notificationDTO DTO уведомления.
     * @return DTO запроса на уведомление.
     *//*

    NotificationRequestDTO toNotificationRequestDTO(NotificationDTO notificationDTO);

    */
/**
     * Преобразует {@link ShoppingListRequestDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListRequestDTO DTO запроса на создание списка покупок.
     * @return Сущность списка покупок.
     *//*

    ShoppingList toShoppingList(ShoppingListRequestDTO shoppingListRequestDTO);

    */
/**
     * Преобразует {@link ShoppingList} в {@link ShoppingListRequestDTO}.
     *
     * @param shoppingList Сущность списка покупок.
     * @return DTO запроса на создание списка покупок.
     *//*

    ShoppingListRequestDTO toShoppingListRequestDTO(ShoppingList shoppingList);

    */
/**
     * Преобразует {@link ShoppingListResponseDTO} в сущность {@link ShoppingList}.
     *
     * @param shoppingListResponseDTO DTO ответа со списком покупок.
     * @return Сущность списка покупок.
     *//*

    ShoppingList toShoppingList(ShoppingListResponseDTO shoppingListResponseDTO);

    */
/**
     * Преобразует {@link MenuPlannerShoppingListRequestDTO} в {@link ShoppingListRequestDTO}.
     *
     * @param requestDTO DTO запроса от планировщика меню.
     * @return DTO запроса на создание списка покупок.
     *//*

    ShoppingListRequestDTO toShoppingListRequestDTO(MenuPlannerShoppingListRequestDTO requestDTO);
}
*/

package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import com.plan_menu.shopping.enums.MeasurementUnit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingListMapper {

    public ShoppingListDTO toShoppingListDTO(ShoppingList shoppingList) {
        if (shoppingList == null) {
            return null;
        }

        return new ShoppingListDTO(
                shoppingList.getId(),
                shoppingList.getName(),
                shoppingList.getDescription(),
                shoppingList.getCreatedDate(),
                shoppingList.getStatus(),
                shoppingList.getCollectionStatus(),
                shoppingList.getReadinessStatus(),
                shoppingList.getUserId(),
                shoppingList.getMealPlanId(),
                toShoppingListItemDTOs(shoppingList.getItems())
        );
    }

    public ShoppingList toShoppingList(ShoppingListDTO shoppingListDTO) {
        if (shoppingListDTO == null) {
            return null;
        }

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListDTO.id());
        shoppingList.setName(shoppingListDTO.name());
        shoppingList.setDescription(shoppingListDTO.description());
        shoppingList.setCreatedDate(shoppingListDTO.createdDate());
        shoppingList.setStatus(shoppingListDTO.status());
        shoppingList.setCollectionStatus(shoppingListDTO.collectionStatus());
        shoppingList.setReadinessStatus(shoppingListDTO.readinessStatus());
        shoppingList.setUserId(shoppingListDTO.userId());
        shoppingList.setMealPlanId(shoppingListDTO.mealPlanId());
        shoppingList.setItems(toShoppingListItems(shoppingListDTO.items()));

        return shoppingList;
    }

    public ShoppingListItemDTO toShoppingListItemDTO(ShoppingListItem shoppingListItem) {
        if (shoppingListItem == null) {
            return null;
        }

        return new ShoppingListItemDTO(
                shoppingListItem.getProduct().getId(),
                shoppingListItem.getQuantity(),
                shoppingListItem.getUnit(),
                shoppingListItem.getShoppingList().getId()
        );
    }

    public ShoppingListItem toShoppingListItem(ShoppingListItemDTO shoppingListItemDTO) {
        if (shoppingListItemDTO == null) {
            return null;
        }

        ShoppingListItem shoppingListItem = new ShoppingListItem();
        shoppingListItem.setId(shoppingListItemDTO.productId());
        shoppingListItem.setQuantity(shoppingListItemDTO.quantity());
        shoppingListItem.setUnit(shoppingListItemDTO.unit());

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListItemDTO.shoppingListId());
        shoppingListItem.setShoppingList(shoppingList);

        Product product = new Product();
        product.setId(shoppingListItemDTO.productId());
        shoppingListItem.setProduct(product);

        return shoppingListItem;
    }

    public ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getWeightPerPack(),
                product.getDescriptionShort(),
                product.getCountOnStorage(),
                product.getUnit(),
                product.getStatus()
        );
    }

    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.id());
        product.setTitle(productDTO.title());
        product.setWeightPerPack(productDTO.weightPerPack());
        product.setDescriptionShort(productDTO.descriptionShort());
        product.setCountOnStorage(productDTO.countOnStorage());
        product.setUnit(productDTO.unit());
        product.setStatus(productDTO.status());

        return product;
    }

    public List<ShoppingListItemDTO> toShoppingListItemDTOs(List<ShoppingListItem> shoppingListItems) {
        if (shoppingListItems == null) {
            return null;
        }

        return shoppingListItems.stream()
                .map(this::toShoppingListItemDTO)
                .collect(Collectors.toList());
    }

    public List<ShoppingListItem> toShoppingListItems(List<ShoppingListItemDTO> shoppingListItemDTOs) {
        if (shoppingListItemDTOs == null) {
            return null;
        }

        return shoppingListItemDTOs.stream()
                .map(this::toShoppingListItem)
                .collect(Collectors.toList());
    }

    public ShoppingListResponseDTO toShoppingListResponseDTO(ShoppingList shoppingList) {
        if (shoppingList == null) {
            return null;
        }

        return new ShoppingListResponseDTO(
                shoppingList.getId(),
                shoppingList.getName(),
                shoppingList.getDescription(),
                shoppingList.getCreatedDate(),
                shoppingList.getStatus(),
                shoppingList.getCollectionStatus(),
                shoppingList.getReadinessStatus(),
                shoppingList.getMealPlanId(),
                toShoppingListItemDTOs(shoppingList.getItems())
        );
    }

    public NotificationDTO toNotificationDTO(NotificationRequestDTO notificationRequestDTO) {
        if (notificationRequestDTO == null) {
            return null;
        }

        return new NotificationDTO(
                notificationRequestDTO.userId(),
                notificationRequestDTO.message()
        );
    }

    public NotificationRequestDTO toNotificationRequestDTO(NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return null;
        }

        return new NotificationRequestDTO(
                notificationDTO.userId(),
                notificationDTO.message(),
                null // notificationType is not present in NotificationDTO
        );
    }

    public ShoppingList toShoppingList(ShoppingListRequestDTO shoppingListRequestDTO) {
        if (shoppingListRequestDTO == null) {
            return null;
        }

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(null); // ID will be generated by the database
        shoppingList.setName(shoppingListRequestDTO.name());
        shoppingList.setDescription(shoppingListRequestDTO.description());
        shoppingList.setCreatedDate(LocalDateTime.now());
        shoppingList.setStatus("NEW");
        shoppingList.setCollectionStatus("NOT_STARTED");
        shoppingList.setReadinessStatus("NOT_READY");
        shoppingList.setUserId(shoppingListRequestDTO.userId());
        shoppingList.setMealPlanId(shoppingListRequestDTO.mealPlanId());
        shoppingList.setItems(new ArrayList<>()); // Items will be added separately

        return shoppingList;
    }

    public ShoppingListRequestDTO toShoppingListRequestDTO(ShoppingList shoppingList) {
        if (shoppingList == null) {
            return null;
        }

        return new ShoppingListRequestDTO(
                shoppingList.getName(),
                shoppingList.getDescription(),
                shoppingList.getMealPlanId(),
                shoppingList.getUserId(),
                toShoppingListItemRequestDTOs(shoppingList.getItems())
        );
    }

    public ShoppingList toShoppingList(ShoppingListResponseDTO shoppingListResponseDTO) {
        if (shoppingListResponseDTO == null) {
            return null;
        }

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(null); // ID will be generated by the database
        shoppingList.setName(shoppingListResponseDTO.name());
        shoppingList.setDescription(shoppingListResponseDTO.description());
        shoppingList.setCreatedDate(LocalDateTime.now());
        shoppingList.setStatus("NEW");
        shoppingList.setCollectionStatus("NOT_STARTED");
        shoppingList.setReadinessStatus("NOT_READY");
        shoppingList.setMealPlanId(shoppingListResponseDTO.mealPlanId());
        shoppingList.setItems(new ArrayList<>()); // Items will be added separately

        return shoppingList;
    }

    public ShoppingListRequestDTO toShoppingListRequestDTO(MenuPlannerShoppingListRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        List<ShoppingListItemRequestDTO> items = new ArrayList<>();
        for (Map.Entry<String, Double> entry : requestDTO.shoppingList().entrySet()) {
            items.add(new ShoppingListItemRequestDTO(
                    null, // productId is not available in MenuPlannerShoppingListRequestDTO
                    entry.getValue().intValue(),
                    MeasurementUnit.PIECE // Assuming default unit, as it's not provided in the request
            ));
        }

        return new ShoppingListRequestDTO(
                null, // name is not available in MenuPlannerShoppingListRequestDTO
                null, // description is not available in MenuPlannerShoppingListRequestDTO
                null, // mealPlanId is not available in MenuPlannerShoppingListRequestDTO
                requestDTO.userId(),
                items
        );
    }

    private List<ShoppingListItemRequestDTO> toShoppingListItemRequestDTOs(List<ShoppingListItem> shoppingListItems) {
        if (shoppingListItems == null) {
            return null;
        }

        return shoppingListItems.stream()
                .map(item -> new ShoppingListItemRequestDTO(
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getUnit()
                ))
                .collect(Collectors.toList());
    }
}
