package org.aston.client;

import org.aston.dto.NotificationDtoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-gateway")
public interface EurekaClient {
    @PostMapping("POST /telegram-bot/send-notification")
    void sendNotificationToTelegramBot(@RequestBody NotificationDtoRequest notificationReq);
}
