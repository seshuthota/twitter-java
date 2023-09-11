package com.codepup.twitter.service;

import com.codepup.twitter.model.Notification;
import com.codepup.twitter.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Optional<Notification> findUserById(Long id) {
        return notificationRepository.findById(id);
    }

}
