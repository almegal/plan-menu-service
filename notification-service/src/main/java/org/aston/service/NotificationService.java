package org.aston.service;

import lombok.AllArgsConstructor;
import org.aston.client.SendMail;
import org.aston.dto.NotificationDtoRequest;
import org.aston.dto.NotificationResponse;
import org.aston.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import static org.aston.mapper.NotificationMapper.mapDtoToEntity;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationResponse createNotification(NotificationDtoRequest dtoRequest) {
        notifyUser(dtoRequest);
        return new NotificationResponse(repository.save(
                mapDtoToEntity(dtoRequest)).getNotificationId());
    }

    private void notifyUser(NotificationDtoRequest request) {
        SendMail.sendMail(request);
    }
}
