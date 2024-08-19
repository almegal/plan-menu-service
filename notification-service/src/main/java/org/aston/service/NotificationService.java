package org.aston.service;

import lombok.RequiredArgsConstructor;
import org.aston.client.EurekaClient;
import org.aston.dto.NotificationDtoRequest;
import org.aston.dto.NotificationResponse;
import org.aston.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import static org.aston.mapper.NotificationMapper.mapDtoToEntity;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final EurekaClient apiGatewayClient;
    public NotificationResponse createNotification(NotificationDtoRequest dtoRequest) {
        notifyUser(dtoRequest);
        return new NotificationResponse(repository.save(
                mapDtoToEntity(dtoRequest)).getNotificationId());
    }

    private void notifyUser(NotificationDtoRequest request) {
        apiGatewayClient.sendNotificationToTelegramBot(request);
    }

}
