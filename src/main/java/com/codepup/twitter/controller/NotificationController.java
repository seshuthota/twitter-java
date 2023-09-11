package com.codepup.twitter.controller;

import com.codepup.twitter.service.NotificationService;
import com.codepup.twitter.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = Collections.singletonList(notificationService.findUserById(userId).get());
        return ResponseEntity.ok(notifications);
    }
}
