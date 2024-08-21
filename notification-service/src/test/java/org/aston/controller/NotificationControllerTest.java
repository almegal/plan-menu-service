package org.aston.controller;

import org.aston.dto.NotificationDtoRequest;
import org.aston.dto.NotificationResponse;
import org.aston.service.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
    /** @noinspection SpringJavaInjectionPointsAutowiringInspection*/
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    @DisplayName("Test notifying user")
    void testNotifyUser() throws Exception {
        NotificationResponse response = new NotificationResponse(1L);

        when(notificationService.createNotification(any(NotificationDtoRequest.class))).thenReturn(response);

        mockMvc.perform(post("/notify")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"message\":\"Test message\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.notificationId").value(1));
    }
}
