package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса уведомлений.
 * Обрабатывает операции с уведомлениями.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {
        System.out.println("Уведомление отправлено пользователю " + notificationRequestDTO.userId() + ": " + notificationRequestDTO.message());
    }

    @Override
    public void sendNotificationToStaff(String message) {
        System.out.println("Уведомление для сотрудников: " + message);
    }

    @Override
    public void sendNotificationToUser(Long userId, String message) {
        System.out.println("Уведомление для пользователя " + userId + ": " + message);
    }

    @Override
    public void sendErrorNotification(ErrorDTO errorDTO) {
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(
                errorDTO.userId(),
                errorDTO.message(),
                "ERROR"
        );
        sendNotification(notificationRequestDTO);
    }
}
