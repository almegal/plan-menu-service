package com.plan_menu.shopping.service.impl;

import com.plan_menu.shopping.dto.ErrorDTO;
import com.plan_menu.shopping.dto.NotificationRequestDTO;
import com.plan_menu.shopping.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Реализация сервиса уведомлений.
 * Обрабатывает отправку уведомлений пользователям через внешний микросервис Notification Service.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate;

    /**
     * Конструктор для инъекции зависимости RestTemplate.
     *
     * @param restTemplate объект RestTemplate для выполнения HTTP-запросов
     */
    public NotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Отправляет уведомление пользователю через внешний микросервис Notification Service.
     *
     * @param notificationRequestDTO данные для отправки уведомления
     */
    @Override
    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {
        HttpHeaders headers = createJsonHeaders();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(notificationRequestDTO, headers);
        restTemplate.postForEntity(notificationServiceUrl + "/send", request, Void.class);
    }

    /**
     * Отправляет уведомление об ошибке пользователю через внешний микросервис Notification Service.
     *
     * @param errorNotificationRequestDTO данные для отправки уведомления об ошибке
     */
    @Override
    public void sendErrorNotification(ErrorDTO errorNotificationRequestDTO) {
        HttpHeaders headers = createJsonHeaders();
        HttpEntity<ErrorDTO> request = new HttpEntity<>(errorNotificationRequestDTO, headers);
        restTemplate.postForEntity(notificationServiceUrl + "/send-error", request, Void.class);
    }

    /**
     * Создает HTTP-заголовки с типом содержимого JSON.
     *
     * @return объект HttpHeaders с установленным типом содержимого JSON
     */
    private HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
