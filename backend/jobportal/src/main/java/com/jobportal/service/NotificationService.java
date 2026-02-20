package com.jobportal.service;

import com.jobportal.model.Notification;
import com.jobportal.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public void createNotification(Long userId, String message) {

        Notification notification = Notification.builder()
                .userId(userId)
                .message(message)
                .readStatus(false)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Long getUnreadCount(Long userId) {
        return repository.countByUserIdAndReadStatusFalse(userId);
    }

    public void markAsRead(Long notificationId) {
        Notification n = repository.findById(notificationId).orElseThrow();
        n.setReadStatus(true);
        repository.save(n);
    }
}
