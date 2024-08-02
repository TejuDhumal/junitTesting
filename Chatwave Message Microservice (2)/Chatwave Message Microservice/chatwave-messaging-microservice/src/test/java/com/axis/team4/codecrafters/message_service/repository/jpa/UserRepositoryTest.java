package com.axis.team4.codecrafters.message_service.repository.jpa;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.message_service.modal.User;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setEmail("user1@example.com");
        user1.setFull_name("John Doe");

        user2 = new User();
        user2.setId(2);
        user2.setEmail("user2@example.com");
        user2.setFull_name("Jane Doe");
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user1));

        Optional<User> foundUser = userRepository.findByEmail("user1@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("user1@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testSearchUsers() {
        when(userRepository.searchUsers(anyString())).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userRepository.searchUsers("Doe");

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getFull_name());
        assertEquals("Jane Doe", users.get(1).getFull_name());
    }
}
