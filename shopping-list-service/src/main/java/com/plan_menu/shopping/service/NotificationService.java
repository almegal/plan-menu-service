package com.plan_menu.shopping.service;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;

/**
 * Интерфейс для сервиса уведомлений.
 * Предоставляет методы для отправки уведомлений пользователям.
 */
public interface NotificationService {

    /**
     * Отправляет уведомление пользователю.
     *
     * @param notificationRequestDTO данные для отправки уведомления
     */
    void sendNotification(NotificationRequestDTO notificationRequestDTO);

    /**
     * Отправляет уведомление об ошибке пользователю.
     *
     * @param errorNotificationRequestDTO данные для отправки уведомления об ошибке
     */
    void sendErrorNotification(ErrorDTO errorNotificationRequestDTO);
}
