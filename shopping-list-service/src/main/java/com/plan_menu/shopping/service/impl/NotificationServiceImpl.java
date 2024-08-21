package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.feign.NotificationClient;
import com.plan_menu.shopping.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса уведомлений.
 * Обрабатывает операции с уведомлениями.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationClient notificationClient;

    /**
     * Конструктор для внедрения зависимости NotificationClient.
     *
     * @param notificationClient клиент для отправки уведомлений через Feign
     */
    public NotificationServiceImpl(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @Override
    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {
        // Отправка уведомления через Feign клиент
        notificationClient.sendNotification(notificationRequestDTO);
        System.out.println("Уведомление отправлено пользователю " + notificationRequestDTO.userId() + ": " + notificationRequestDTO.message());
    }

    @Override
    public void sendNotificationToStaff(String message) {
        // Логика отправки уведомления сотрудникам
        // Примечание: Для отправки уведомлений сотрудникам используем фиксированный тип уведомления
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(
                null, // Поскольку это не для конкретного пользователя, ID можно не задавать
                message,
                "INFO" // Примерный тип уведомления, который может быть определен в вашем бизнес-процессе
        );
        sendNotification(notificationRequestDTO);
    }

    @Override
    public void sendNotificationToUser(Long userId, String message) {
        // Создание DTO для отправки уведомления пользователю
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(
                userId,
                message,
                "INFO" // Примерный тип уведомления
        );
        sendNotification(notificationRequestDTO);
    }

    @Override
    public void sendErrorNotification(ErrorDTO errorDTO) {
        // Создание DTO для отправки уведомления об ошибке
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(
                errorDTO.userId(),
                errorDTO.message(), // Используем message из ErrorDTO для уведомления
                "ERROR" // Тип уведомления для ошибок
        );
        sendNotification(notificationRequestDTO);
    }
}
