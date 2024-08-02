package com.axis.team4.codecrafters.user_service.modal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@Disabled
class NotificationTest {

    @Mock
    private User mockUser;

    private Notification notification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notification = new Notification();
    }

    @Test
    void testSetAndGetId() {
        notification.setId(1);
        assertEquals(1, notification.getId());
    }

    @Test
    void testSetAndGetMessage() {
        String message = "Test Message";
        notification.setMessage(message);
        assertEquals(message, notification.getMessage());
    }

    @Test
    void testSetAndGetIsSeen() {
        notification.setIsSeen(true);
        assertEquals(true, notification.getIsSeen());
    }

    @Test
    void testSetAndGetTimestamp() {
        LocalDateTime timestamp = LocalDateTime.now();
        notification.setTimestamp(timestamp);
        assertEquals(timestamp, notification.getTimestamp());
    }

    @Test
    void testSetAndGetUser() {
        notification.setUser(mockUser);
        assertEquals(mockUser, notification.getUser());
    }

    @Test
    void testNotificationConstructor() {
        LocalDateTime timestamp = LocalDateTime.now();
        Notification notification = new Notification(1, "Test Message", true, timestamp, mockUser);

        assertEquals(1, notification.getId());
        assertEquals("Test Message", notification.getMessage());
        assertEquals(true, notification.getIsSeen());
        assertEquals(timestamp, notification.getTimestamp());
        assertEquals(mockUser, notification.getUser());
    }
}
