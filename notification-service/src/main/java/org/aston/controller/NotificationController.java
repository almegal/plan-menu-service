package org.aston.controller;

import lombok.RequiredArgsConstructor;
import org.aston.dto.NotificationDtoRequest;
import org.aston.dto.NotificationResponse;
import org.aston.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<NotificationResponse> notifyUser(@RequestBody NotificationDtoRequest dtoRequest) {
        return ResponseEntity.ok(notificationService.createNotification(dtoRequest));
    }
}
