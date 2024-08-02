package com.axis.team4.codecrafters.message_service.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import com.axis.team4.codecrafters.message_service.config.JwtTokenProvider;
import com.axis.team4.codecrafters.message_service.exception.UserException;
import com.axis.team4.codecrafters.message_service.modal.User;
import com.axis.team4.codecrafters.message_service.repository.jpa.UserRepository;
import com.axis.team4.codecrafters.message_service.request.UpdateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
//@Disabled
class UserServiceImplementationTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepo;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateUser() throws UserException {
        User user = new User();
        user.setId(1);
        user.setFull_name("Old Name");
        user.setProfile_picture("old_picture.jpg");

        UpdateUserRequest req = new UpdateUserRequest();
        req.setFull_name("New Name");
        req.setProfile_picture("new_picture.jpg");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = userService.updateUser(1, req);

        assertNotNull(updatedUser);
        assertEquals("New Name", updatedUser.getFull_name());
        assertEquals("new_picture.jpg", updatedUser.getProfile_picture());
        verify(userRepo, times(1)).save(updatedUser);
    }

    @Test
    void testFindUserById() throws UserException {
        User user = new User();
        user.setId(1);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1);

        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
    }

    @Test
    void testFindUserById_NotFound() {
        when(userRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.findUserById(1));
    }

    @Test
    void testFindUserProfile() {
        User user = new User();
        user.setEmail("test@example.com");

        String jwt = "valid.jwt.token";

        when(jwtTokenProvider.getEmailFromToken(jwt)).thenReturn("test@example.com");
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User foundUser = userService.findUserProfile(jwt);

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void testFindUserProfile_InvalidToken() {
        String jwt = "invalid.jwt.token";

        when(jwtTokenProvider.getEmailFromToken(jwt)).thenReturn("invalid@example.com");
        when(userRepo.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        assertThrows(BadCredentialsException.class, () -> userService.findUserProfile(jwt));
    }

    @Test
    void testSearchUser() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        String query = "test";

        when(userRepo.searchUsers(query)).thenReturn(users);

        List<User> foundUsers = userService.searchUser(query);

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        verify(userRepo, times(1)).searchUsers(query);
    }
}
