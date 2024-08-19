package com.plan_menu.meal_plan_service.service.impl;

import com.plan_menu.meal_plan_service.dto.NotificationDto;
import com.plan_menu.meal_plan_service.feign.NotificationServiceClient;
import com.plan_menu.meal_plan_service.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationServiceClient notificationServiceClient;

    public NotificationServiceImpl(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    @Override
    public HttpStatus sendNotification(NotificationDto dto) {
        return notificationServiceClient.sendNotification(dto);
    }
}
