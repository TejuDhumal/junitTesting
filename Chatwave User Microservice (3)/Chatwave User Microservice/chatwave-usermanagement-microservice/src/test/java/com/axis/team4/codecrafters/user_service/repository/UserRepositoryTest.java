package com.axis.team4.codecrafters.user_service.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.user_service.modal.User;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setUsername("testuser");
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testFindByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByUsername("testuser");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testSearchUsers() {
        when(userRepository.searchUsers(anyString())).thenReturn(List.of(user));

        List<User> foundUsers = userRepository.searchUsers("test");
        assertThat(foundUsers).isNotEmpty();
        assertThat(foundUsers.get(0).getUsername()).isEqualTo("testuser");
    }
}
