package com.plan_menu.shopping.mapper;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.*;
import com.plan_menu.shopping.enums.MeasurementUnit;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
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
