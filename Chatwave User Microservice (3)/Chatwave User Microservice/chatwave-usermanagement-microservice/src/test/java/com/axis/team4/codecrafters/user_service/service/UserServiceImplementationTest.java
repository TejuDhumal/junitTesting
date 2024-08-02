package com.axis.team4.codecrafters.user_service.service;

import com.axis.team4.codecrafters.user_service.exception.UserException;
import com.axis.team4.codecrafters.user_service.modal.User;
import com.axis.team4.codecrafters.user_service.repository.UserRepository;
import com.axis.team4.codecrafters.user_service.request.UpdateUserRequest;
import com.axis.team4.codecrafters.user_service.config.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//@Disabled
@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setFullName("Test User");
    }

    @Test
    void findUserProfile() throws UserException {
        when(jwtTokenProvider.getEmailFromToken(anyString())).thenReturn("test@example.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        User result = userService.findUserProfile("token");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void findUserProfile_UserNotFound() {
        when(jwtTokenProvider.getEmailFromToken(anyString())).thenReturn("test@example.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.findUserProfile("token"));
    }

    @Test
    void updateUser() throws UserException {
        UpdateUserRequest req = new UpdateUserRequest();
        req.setFullName("Updated Name");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User result = userService.updateUser(1, req);

        assertNotNull(result);
        assertEquals("Updated Name", result.getFullName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_UserNotFound() {
        UpdateUserRequest req = new UpdateUserRequest();

        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.updateUser(1, req));
    }

    @Test
    void findUserById() throws UserException {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User result = userService.findUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findUserById_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.findUserById(1));
    }

    @Test
    void searchUser() {
        // Add your test cases for searchUser method here
    }

    @Test
    void deactivateUser() throws UserException, MessagingException, jakarta.mail.MessagingException {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        userService.deactivateUser("test@example.com");

        verify(userRepository, times(1)).save(any(User.class));
        verify(emailService, times(1)).sendReactivateOtp(anyString(), anyString());
    }

    @Test
    void deactivateUser_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.deactivateUser("test@example.com"));
    }

    @Test
    void reactivateUser() throws UserException {
        user.setOtp("123456");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        userService.reactivateUser("test@example.com", "123456");

        assertEquals(1, user.isVerify());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void reactivateUser_InvalidOtp() {
        user.setOtp("123456");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        assertThrows(UserException.class, () -> userService.reactivateUser("test@example.com", "654321"));
    }

    @Test
    void forgetPassword() throws UserException, MessagingException, jakarta.mail.MessagingException {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        userService.forgetPassword("test@example.com");

        verify(userRepository, times(1)).save(any(User.class));
        verify(emailService, times(1)).sendResetPasswordOtp(anyString(), anyString());
    }

    @Test
    void forgetPassword_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.forgetPassword("test@example.com"));
    }

    @Test
    void resetPassword() throws UserException {
        user.setOtp("123456");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        userService.resetPassword("test@example.com", "123456", "newPassword");

        verify(userRepository, times(1)).save(any(User.class));
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void resetPassword_InvalidOtp() {
        user.setOtp("123456");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        assertThrows(UserException.class, () -> userService.resetPassword("test@example.com", "654321", "newPassword"));
    }
}
