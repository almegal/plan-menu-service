package com.plan_menu.meal_plan_service.service;

import com.plan_menu.meal_plan_service.dto.NotificationDto;
import org.springframework.http.HttpStatus;

/**
 * Интерфейс для сервиса уведомлений, предоставляющий метод для отправки уведомлений пользователям.
 */
public interface NotificationService {

    /**
     * Отправляет уведомление пользователю на основе переданного объекта NotificationDto.
     *
     * @param dto объект NotificationDto, содержащий информацию об уведомлении, которое необходимо отправить.
     * @return HttpStatus, представляющий статус ответа от сервиса уведомлений.
     */
    HttpStatus sendNotification(NotificationDto dto);
}
