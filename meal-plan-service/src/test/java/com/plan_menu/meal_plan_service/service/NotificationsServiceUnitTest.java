package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.NotificationDto;
import com.plan_menu.meal_plan_service.feign.NotificationServiceClient;
import com.plan_menu.meal_plan_service.service.impl.NotificationServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class NotificationsServiceUnitTest {
    @Mock
    NotificationServiceClient client;

    @InjectMocks
    NotificationServiceImpl service;

    @DisplayName("Выбрасывает исключение если запрос не правильный ")
    @Test
    public void shouldReturnExceptionWhenSomethingWentWrong() {
        //
        doThrow(FeignException.class).when(client).sendNotification(any());
        //
        assertThrows(FeignException.class, () -> {
            service.sendNotification(new NotificationDto(null, null));
        });
    }
}
