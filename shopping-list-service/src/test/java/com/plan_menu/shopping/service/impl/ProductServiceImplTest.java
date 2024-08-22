package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ProductAvailabilityResponseDTO;
import com.plan_menu.shopping.dto.ProductDTO;
import com.plan_menu.shopping.entity.Product;
import com.plan_menu.shopping.entity.ShoppingList;
import com.plan_menu.shopping.entity.ShoppingListItem;
import com.plan_menu.shopping.exception.ProductNotFoundException;
import com.plan_menu.shopping.mapper.ShoppingListMapper;
import com.plan_menu.shopping.repository.ProductRepository;
import com.plan_menu.shopping.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingListMapper shoppingListMapper;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckProductAvailability() {
        Long productId = 1L;
        int count = 5;
        when(productRepository.isProductAvailable(productId, count)).thenReturn(true);

        ProductAvailabilityResponseDTO response = productService.checkProductAvailability(productId, count);

        assertTrue(response.available());
        assertEquals(productId, response.productId());
    }

//    @Test
//    public void testGetProductById() {
//        Long productId = 1L;
//        Product product = new Product();
//        product.setId(productId);
//        ProductDTO productDTO = new ProductDTO(productId, "Test Product", 1.0, "Short description", 10, null);
//
//        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//        when(shoppingListMapper.toProductDTO(product)).thenReturn(productDTO);
//
//        ProductDTO result = productService.getProductById(productId);
//
//        assertEquals(productId, result.id());
//    }

    @Test
    public void testGetProductByIdNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(productId);
        });
    }

//    @Test
//    public void testSearchProductsByTitle() {
//        String titlePart = "test";
//        Product product1 = new Product();
//        product1.setId(1L);
//        Product product2 = new Product();
//        product2.setId(2L);
//        List<Product> products = Arrays.asList(product1, product2);
//
//        ProductDTO productDTO1 = new ProductDTO(1L, "Test Product 1", 1.0, "Short description 1", 10, null);
//        ProductDTO productDTO2 = new ProductDTO(2L, "Test Product 2", 1.0, "Short description 2", 10, null);
//
//        when(productRepository.findByTitleContaining(titlePart)).thenReturn(products);
//        when(shoppingListMapper.toProductDTO(product1)).thenReturn(productDTO1);
//        when(shoppingListMapper.toProductDTO(product2)).thenReturn(productDTO2);
//
//        List<ProductDTO> result = productService.searchProductsByTitle(titlePart);
//
//        assertEquals(2, result.size());
//        assertEquals(1L, result.get(0).id());
//        assertEquals(2L, result.get(1).id());
//    }

    @Test
    public void testPlaceOrder() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(1L);

        productService.placeOrder(shoppingList);

    }

    @Test
    public void testStartCollection() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(1L);
        Product product = new Product();
        product.setId(1L);
        product.setStatus("AVAILABLE");
        ShoppingListItem item = new ShoppingListItem();
        item.setProduct(product);
        item.setQuantity(5);
        shoppingList.setItems(Arrays.asList(item));

        when(productRepository.isProductAvailable(1L, 5)).thenReturn(true);

        productService.startCollection(shoppingList);

        assertEquals("IN_COLLECTION", product.getStatus());
        verify(notificationService, times(1)).sendNotificationToStaff("Начало сборки продуктов для списка покупок: 1");
    }

    @Test
    public void testStartDelivery() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(1L);
        Product product = new Product();
        product.setId(1L);
        product.setStatus("COLLECTED");
        ShoppingListItem item = new ShoppingListItem();
        item.setProduct(product);
        item.setQuantity(5);
        shoppingList.setItems(Arrays.asList(item));

        productService.startDelivery(shoppingList);

        assertEquals("IN_DELIVERY", product.getStatus());
        verify(notificationService, times(1)).sendNotificationToUser(shoppingList.getUserId(), "Доставка продуктов для списка покупок 1 начата");
    }
}
