package com.plan_menu.meal_plan_service.feign;

import com.plan_menu.meal_plan_service.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="notification-service")
public interface NotificationServiceClient {
    @PostMapping("/notifications/")
    HttpStatus sendNotification(NotificationDto notificationDto);
}
