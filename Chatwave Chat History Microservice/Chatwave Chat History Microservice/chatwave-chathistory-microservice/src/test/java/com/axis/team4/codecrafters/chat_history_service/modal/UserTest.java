package com.axis.team4.codecrafters.chat_history_service.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;
    private List<Notification> notifications;

    @BeforeEach
    public void setUp() {
        notifications = new ArrayList<>();
        user = new User(1, "John Doe", "john.doe@example.com", "profile_pic.jpg", "password123", notifications);
    }

    @Test
    public void testUserEntity() {
        // Test getters
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getFull_name());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("profile_pic.jpg", user.getProfile_picture());
        assertEquals("password123", user.getPassword());
        assertEquals(notifications, user.getNotifications());

        // Test setters
        user.setFull_name("Jane Doe");
        assertEquals("Jane Doe", user.getFull_name());
    }
}
