package com.plan_menu.shopping.service;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;

/**
 * Интерфейс для сервиса уведомлений.
 * Предоставляет методы для отправки уведомлений пользователям и сотрудникам.
 */
public interface NotificationService {

    /**
     * Отправляет уведомление пользователю.
     *
     * @param notificationRequestDTO DTO с данными для отправки уведомления
     */
    void sendNotification(NotificationRequestDTO notificationRequestDTO);

    /**
     * Отправляет уведомление сотрудникам.
     *
     * @param message сообщение для сотрудников
     */
    void sendNotificationToStaff(String message);

    /**
     * Отправляет уведомление пользователю.
     *
     * @param userId идентификатор пользователя
     * @param message сообщение для пользователя
     */
    void sendNotificationToUser(Long userId, String message);

    /**
     * Отправляет уведомление об ошибке пользователю.
     *
     * @param errorDTO DTO с данными об ошибке
     */
    void sendErrorNotification(ErrorDTO errorDTO);
}
