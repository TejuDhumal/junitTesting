package com.axis.team4.codecrafters.message_service.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@Disabled
public class NotificationTest {

    private Notification notification;
    private User mockUser;
    private Integer id = 1;
    private String message = "Test message";
    private Boolean isSeen = true;
    private LocalDateTime timestamp = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        mockUser = mock(User.class);
        notification = new Notification(id, message, isSeen, timestamp, mockUser);
    }

    @Test
    public void testGetId() {
        assertEquals(id, notification.getId());
    }

    @Test
    public void testSetId() {
        Integer newId = 2;
        notification.setId(newId);
        assertEquals(newId, notification.getId());
    }

    @Test
    public void testGetMessage() {
        assertEquals(message, notification.getMessage());
    }

    @Test
    public void testSetMessage() {
        String newMessage = "New test message";
        notification.setMessage(newMessage);
        assertEquals(newMessage, notification.getMessage());
    }

    @Test
    public void testGetIsSeen() {
        assertEquals(isSeen, notification.getIs_seen());
    }

    @Test
    public void testSetIsSeen() {
        Boolean newIsSeen = false;
        notification.setIs_seen(newIsSeen);
        assertEquals(newIsSeen, notification.getIs_seen());
    }

    @Test
    public void testGetTimestamp() {
        assertEquals(timestamp, notification.getTimestamp());
    }

    @Test
    public void testSetTimestamp() {
        LocalDateTime newTimestamp = LocalDateTime.now().plusDays(1);
        notification.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, notification.getTimestamp());
    }

    @Test
    public void testGetUser() {
        assertEquals(mockUser, notification.getUser());
    }

    @Test
    public void testSetUser() {
        User newUser = mock(User.class);
        notification.setUser(newUser);
        assertEquals(newUser, notification.getUser());
    }
}
