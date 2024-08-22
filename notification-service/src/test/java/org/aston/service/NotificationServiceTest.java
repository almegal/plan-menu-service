package org.aston.service;

import org.aston.dto.NotificationDtoRequest;
import org.aston.dto.NotificationResponse;
import org.aston.entity.Notification;
import org.aston.mapper.NotificationMapper;
import org.aston.repository.NotificationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {
    @Mock
    private NotificationRepository repository;
    @InjectMocks
    private NotificationService service;

    @Test
    @DisplayName("Test creating notification for user")
    void createNotificationTest() {
        NotificationDtoRequest dtoRequest = new NotificationDtoRequest(
                2L, "Test Notification");
        Notification notification = NotificationMapper.mapDtoToEntity(dtoRequest);
        notification.setNotificationId(1L);
        when(repository.save(any(Notification.class))).thenReturn(notification);

        NotificationResponse response = service.createNotification(dtoRequest);

        assertThat(response.notificationId()).isEqualTo(1L);
        verify(repository).save(any(Notification.class));
    }
}
