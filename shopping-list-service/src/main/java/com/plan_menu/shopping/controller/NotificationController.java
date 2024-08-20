package com.plan_menu.shopping.controller;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления уведомлениями.
 * Предоставляет методы для отправки уведомлений пользователю.
 */
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Отправляет уведомление пользователю.
     *
     * @param notificationRequestDTO данные для отправки уведомления
     * @return ResponseEntity с результатом отправки уведомления
     */
    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        notificationService.sendNotification(notificationRequestDTO);
        return ResponseEntity.ok("Notification sent successfully.");
    }

    /**
     * Обрабатывает ошибки и отправляет уведомление об ошибке пользователю.
     *
     * @param errorNotificationRequestDTO данные для отправки уведомления об ошибке
     * @return ResponseEntity с результатом отправки уведомления об ошибке
     */
    @PostMapping("/error")
    public ResponseEntity<String> handleErrorNotifications(@RequestBody ErrorDTO errorNotificationRequestDTO) {
        try {
            notificationService.sendErrorNotification(errorNotificationRequestDTO);
            return ResponseEntity.ok("Error notification sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send error notification: " + e.getMessage());
        }
    }
}
