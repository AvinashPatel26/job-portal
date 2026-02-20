package com.jobportal.controller;

import com.jobportal.model.Notification;
import com.jobportal.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class NotificationController {

    private final NotificationService service;

    @GetMapping
    public List<Notification> getNotifications(
            @RequestParam Long userId) {

        return service.getUserNotifications(userId);
    }

    @GetMapping("/unread-count")
    public Long unreadCount(
            @RequestParam Long userId) {

        return service.getUnreadCount(userId);
    }

    @PutMapping("/{id}/read")
    public void markRead(@PathVariable Long id) {
        service.markAsRead(id);
    }
}
