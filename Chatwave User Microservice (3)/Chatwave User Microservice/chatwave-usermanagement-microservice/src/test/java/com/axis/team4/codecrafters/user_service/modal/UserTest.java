package com.axis.team4.codecrafters.user_service.modal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@Disabled
public class UserTest {

    private User user;
    private List<Notification> notifications;

    @BeforeEach
    void setUp() {
        notifications = new ArrayList<>();
        user = new User(1, "John Doe", "johndoe", "john@example.com", "profile.png", "password", notifications, "Bio", "Active");
    }

    @Test
    void testConstructor() {
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getFullName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("profile.png", user.getProfilePicture());
        assertEquals("password", user.getPassword());
        assertEquals(notifications, user.getNotifications());
        assertEquals("Bio", user.getBio());
        assertEquals("Active", user.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        user.setFullName("Jane Doe");
        assertEquals("Jane Doe", user.getFullName());

        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());

        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());

        user.setProfilePicture("new_profile.png");
        assertEquals("new_profile.png", user.getProfilePicture());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        List<Notification> newNotifications = new ArrayList<>();
        user.setNotifications(newNotifications);
        assertEquals(newNotifications, user.getNotifications());

        user.setBio("New Bio");
        assertEquals("New Bio", user.getBio());

        user.setStatus("Inactive");
        assertEquals("Inactive", user.getStatus());
    }

//    @Disabled
//    @Test
//    void testOnCreate() {
//        user.onCreate();
//        assertNotNull(user.getCreatedAt());
//        assertNotNull(user.getUpdatedAt());
//        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
//    }

    @Test
    void testOnUpdate() {
        User user = new User();
        user.onCreate(); // Set initial creation time
        LocalDateTime createdAt = user.getCreatedAt();

        // Introduce a small delay to ensure the timestamps are different
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        user.onUpdate();
        assertNotNull(user.getUpdatedAt());
        assertNotEquals(createdAt, user.getUpdatedAt());
    }
    @Test
    void testEqualsAndHashCode() {
        User user2 = new User(1, "John Doe", "johndoe", "john@example.com", "profile.png", "password", notifications, "Bio", "Active");

        assertEquals(user, user2);
        assertEquals(user.hashCode(), user2.hashCode());

        user2.setEmail("different@example.com");
        assertNotEquals(user, user2);
        assertNotEquals(user.hashCode(), user2.hashCode());
    }

//    @Disabled
//    @Test
//    void testToString() {
//        String expected = "User [id=1, full_name=John Doe, username=johndoe email=john@example.com, profile_picture=profile.png, password=password, isVerify=0, otp=null, bio=Bio, status=Active, createdAt=null, updatedAt=null, notifications=[]]";
//        assertEquals(expected, user.toString());
//    }
}
