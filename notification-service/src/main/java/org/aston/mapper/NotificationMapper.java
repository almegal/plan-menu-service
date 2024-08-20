package org.aston.mapper;

import org.aston.dto.NotificationDtoRequest;
import org.aston.entity.Notification;

public class NotificationMapper {
    public static Notification mapDtoToEntity(NotificationDtoRequest dtoRequest) {
        Notification notification = new Notification();
        notification.setUserId(dtoRequest.userId());
        notification.setMessage(dtoRequest.message());
        return notification;
    }
}
