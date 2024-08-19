package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.ShoppingListRequestDto;
import com.plan_menu.meal_plan_service.feign.OrderServiceClient;
import com.plan_menu.meal_plan_service.service.impl.OrderServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @Mock
    OrderServiceClient client;
    @InjectMocks
    OrderServiceImpl orderService;

    @DisplayName("Выбрасывает исключение если запрос не правильный ")
    @Test
    public void shouldReturnExceptionWhenSomethingWentWrong() {
        //
        doThrow(FeignException.class).when(client).sendListOrder(any());
        //
        assertThrows(FeignException.class, () -> {
            orderService.makeOrder(new ShoppingListRequestDto(1L, null));
        });
    }
}
