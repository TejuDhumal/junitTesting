package com.axis.team4.codecrafters.message_service.modal;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//@Disabled
class UserTest {

    @InjectMocks
    private User user;

    @Mock
    private List<Notification> notifications;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notifications = new ArrayList<>();
        user = new User(1, "John Doe", "john.doe@example.com", "profile.jpg", "password123", notifications, "johndoe", "This is a bio");
    }

    @Test
    void testGettersAndSetters() {
        user.setId(2);
        assertEquals(2, user.getId());

        user.setFull_name("Jane Doe");
        assertEquals("Jane Doe", user.getFull_name());

        user.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getEmail());

        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword());

        user.setProfile_picture("newprofile.jpg");
        assertEquals("newprofile.jpg", user.getProfile_picture());

        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());

        user.setBio("New bio");
        assertEquals("New bio", user.getBio());

        List<Notification> newNotifications = new ArrayList<>();
        user.setNotifications(newNotifications);
        assertEquals(newNotifications, user.getNotifications());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User(1, "John Doe", "john.doe@example.com", "profile.jpg", "password123", notifications, "johndoe", "This is a bio");
        User user2 = new User(1, "John Doe", "john.doe@example.com", "profile.jpg", "password123", notifications, "johndoe", "This is a bio");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        user2.setEmail("different.email@example.com");
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "User [id=1, full_name=John Doe, username=johndoe, bio =This is a bio, email=john.doe@example.com, notifications=[]]";
        assertEquals(expectedString, user.toString());
    }

    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User(1, "John Doe", "john.doe@example.com", "profile.jpg", "password123", notifications, "johndoe", "This is a bio");
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getFull_name());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("profile.jpg", user.getProfile_picture());
        assertEquals("password123", user.getPassword());
        assertEquals(notifications, user.getNotifications());
        assertEquals("johndoe", user.getUsername());
        assertEquals("This is a bio", user.getBio());
    }
}
