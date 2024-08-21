package org.aston.mapper;

import org.aston.dto.NotificationDtoRequest;
import org.aston.entity.Notification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationMapperTest {
    @Test
    @DisplayName("Test notification mapping from dto to entity")
    void testMapDtoToEntity() {
        NotificationDtoRequest dtoRequest = new NotificationDtoRequest(1L, "Test message");

        Notification notification = NotificationMapper.mapDtoToEntity(dtoRequest);

        assertEquals(1L, notification.getUserId());
        assertEquals("Test message", notification.getMessage());
    }
}
