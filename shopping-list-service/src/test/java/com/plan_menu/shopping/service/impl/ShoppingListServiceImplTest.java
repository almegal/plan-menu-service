package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.*;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.entity.ShoppingList;
import com.plan_menu.shopping.entity.ShoppingListItem;
import com.plan_menu.shopping.repository.ShoppingListRepository;
import com.plan_menu.shopping.repository.ShoppingListItemRepository;
import com.plan_menu.shopping.service.NotificationService;
import com.plan_menu.shopping.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShoppingListServiceImplTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private ShoppingListItemRepository shoppingListItemRepository;

    @Mock
    private ProductService productService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ShoppingListServiceImpl shoppingListService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
////    public void testCreateShoppingList() {
////        ShoppingListRequestDTO requestDTO = new ShoppingListRequestDTO(
////                "Test List",
////                "Test Description",
////                Arrays.asList(new ShoppingListItemRequestDTO(1L, 5, null))
////        );
////
////        ShoppingList shoppingList = new ShoppingList();
////        shoppingList.setId(1L);
////        shoppingList.setName(requestDTO.name());
////        shoppingList.setDescription(requestDTO.description());
////        shoppingList.setCreatedDate(LocalDateTime.now());
////
////        ShoppingListItem item = new ShoppingListItem();
////        Product product = new Product();
////        product.setId(1L);
////        item.setProduct(product);
////        item.setQuantity(5);
////        item.setShoppingList(shoppingList);
////        shoppingList.setItems(Arrays.asList(item));
////
////        when(shoppingListRepository.save(any(ShoppingList.class))).thenReturn(shoppingList);
////
////        ShoppingListResponseDTO responseDTO = shoppingListService.createShoppingList(requestDTO);
////
////        assertEquals(shoppingList.getId(), responseDTO.id());
////        assertEquals(shoppingList.getName(), responseDTO.name());
////        assertEquals(shoppingList.getDescription(), responseDTO.description());
////        assertEquals(shoppingList.getCreatedDate(), responseDTO.createdDate());
////        assertEquals(1, responseDTO.items().size());
////    }
//
//    @Test
//    public void testGetShoppingListById() {
//        Long shoppingListId = 1L;
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setId(shoppingListId);
//        shoppingList.setName("Test List");
//        shoppingList.setDescription("Test Description");
//        shoppingList.setCreatedDate(LocalDateTime.now());
//
//        ShoppingListItem item = new ShoppingListItem();
//        Product product = new Product();
//        product.setId(1L);
//        item.setProduct(product);
//        item.setQuantity(5);
//        item.setShoppingList(shoppingList);
//        shoppingList.setItems(Arrays.asList(item));
//
//        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//
//        ShoppingListResponseDTO responseDTO = shoppingListService.getShoppingListById(shoppingListId);
//
//        assertEquals(shoppingList.getId(), responseDTO.id());
//        assertEquals(shoppingList.getName(), responseDTO.name());
//        assertEquals(shoppingList.getDescription(), responseDTO.description());
//        assertEquals(shoppingList.getCreatedDate(), responseDTO.createdDate());
//        assertEquals(1, responseDTO.items().size());
//    }
//
//    @Test
//    public void testOptimizeShoppingList() {
//        Long shoppingListId = 1L;
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setId(shoppingListId);
//        shoppingList.setName("Test List");
//        shoppingList.setDescription("Test Description");
//        shoppingList.setCreatedDate(LocalDateTime.now());
//
//        ShoppingListItem item = new ShoppingListItem();
//        Product product = new Product();
//        product.setId(1L);
//        item.setProduct(product);
//        item.setQuantity(5);
//        item.setShoppingList(shoppingList);
//        shoppingList.setItems(Arrays.asList(item));
//
//        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//        when(shoppingListRepository.save(any(ShoppingList.class))).thenReturn(shoppingList);
//
//        ShoppingListResponseDTO responseDTO = shoppingListService.optimizeShoppingList(shoppingListId);
//
//        assertEquals(shoppingList.getId(), responseDTO.id());
//        assertEquals(shoppingList.getName(), responseDTO.name());
//        assertEquals(shoppingList.getDescription(), responseDTO.description());
//        assertEquals(shoppingList.getCreatedDate(), responseDTO.createdDate());
//        assertEquals(1, responseDTO.items().size());
//    }
//
//    @Test
//    public void testInitiateProductOrder() {
//        Long shoppingListId = 1L;
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setId(shoppingListId);
//        shoppingList.setUserId(1L);
//
//        ShoppingListItem item = new ShoppingListItem();
//        Product product = new Product();
//        product.setId(1L);
//        item.setProduct(product);
//        item.setQuantity(5);
//        item.setShoppingList(shoppingList);
//        shoppingList.setItems(Arrays.asList(item));
//
//        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//        when(productService.checkProductAvailability(anyLong(), anyInt())).thenReturn(new ProductAvailabilityResponseDTO(1L, true, null));
//
//        shoppingListService.initiateProductOrder(shoppingListId);
//
//        verify(productService, times(1)).placeOrder(shoppingList);
//        verify(notificationService, times(1)).sendNotification(any(NotificationRequestDTO.class));
//    }
//
//    @Test
//    public void testStartProductCollection() {
//        Long shoppingListId = 1L;
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setId(shoppingListId);
//        shoppingList.setUserId(1L);
//
//        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//
//        shoppingListService.startProductCollection(shoppingListId);
//
//        verify(productService, times(1)).startCollection(shoppingList);
//        verify(notificationService, times(1)).sendNotification(any(NotificationRequestDTO.class));
//    }
//
//    @Test
//    public void testStartProductDelivery() {
//        Long shoppingListId = 1L;
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setId(shoppingListId);
//        shoppingList.setUserId(1L);
//
//        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//
//        shoppingListService.startProductDelivery(shoppingListId);
//
//        verify(productService, times(1)).startDelivery(shoppingList);
//        verify(notificationService, times(1)).sendNotification(any(NotificationRequestDTO.class));
//    }
}
