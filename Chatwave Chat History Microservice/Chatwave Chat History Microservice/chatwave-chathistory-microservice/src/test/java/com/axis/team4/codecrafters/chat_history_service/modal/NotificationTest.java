package com.axis.team4.codecrafters.chat_history_service.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationTest {

    private Notification notification;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(); // Assuming a default constructor exists
        notification = new Notification(1, "Test message", false, LocalDateTime.now(), user);
    }

    @Test
    public void testNotificationEntity() {
        // Test getters
        assertEquals(1, notification.getId());
        assertEquals("Test message", notification.getMessage());
        assertEquals(false, notification.getIs_seen());
        assertEquals(user, notification.getUser());

        // Test setters
        notification.setMessage("Updated message");
        assertEquals("Updated message", notification.getMessage());
    }
}
