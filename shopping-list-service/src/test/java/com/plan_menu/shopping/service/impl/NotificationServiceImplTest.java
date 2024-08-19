package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationServiceImplTest {

    private NotificationServiceImpl notificationService;

    @BeforeEach
    public void setUp() {
        notificationService = new NotificationServiceImpl();
    }

    @Test
    public void testSendNotification() {
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(1L, "Test message", "INFO");
        notificationService.sendNotification(notificationRequestDTO);
    }

    @Test
    public void testSendNotificationToStaff() {
        notificationService.sendNotificationToStaff("Staff test message");
    }

    @Test
    public void testSendNotificationToUser() {
        notificationService.sendNotificationToUser(2L, "User test message");
    }

    @Test
    public void testSendErrorNotification() {
        ErrorDTO errorDTO = new ErrorDTO(3L, "ERROR_CODE", "Error message", "Error details");
        notificationService.sendErrorNotification(errorDTO);
    }
}
