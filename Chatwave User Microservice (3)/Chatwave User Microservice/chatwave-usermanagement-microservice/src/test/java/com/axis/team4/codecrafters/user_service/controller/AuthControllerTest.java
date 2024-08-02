package com.axis.team4.codecrafters.user_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.axis.team4.codecrafters.user_service.config.JwtTokenProvider;
import com.axis.team4.codecrafters.user_service.dto.UserDto;
import com.axis.team4.codecrafters.user_service.exception.UserException;
import com.axis.team4.codecrafters.user_service.modal.User;
import com.axis.team4.codecrafters.user_service.repository.UserRepository;
import com.axis.team4.codecrafters.user_service.request.*;
import com.axis.team4.codecrafters.user_service.response.AuthResponse;
import com.axis.team4.codecrafters.user_service.service.CustomUserDetailsService;
import com.axis.team4.codecrafters.user_service.service.EmailService;
import com.axis.team4.codecrafters.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.util.Optional;

//@Disabled
class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private CustomUserDetailsService customUserDetails;

    @Mock
    private EmailService emailService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserHandler() throws UserException, MessagingException, jakarta.mail.MessagingException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFullName("Test User");
        user.setUsername("testuser");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        ResponseEntity<AuthResponse> response = authController.createUserHandler(user);

        verify(userRepository, times(1)).save(any(User.class));
        verify(emailService, times(1)).sendOtp(anyString(), anyString());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("User registered successfully. Please verify your email.", response.getBody().getMessage());
    }

    @Test
    void verifyOtp() {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail("test@example.com");
        otpRequest.setOtp("123456");

        User user = new User();
        user.setEmail("test@example.com");
        user.setOtp("123456");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<AuthResponse> response = authController.verifyOtp(otpRequest);

        verify(userRepository, times(1)).save(any(User.class));

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("OTP verified successfully.", response.getBody().getMessage());
    }

    @Test
    void signin() {
        // Creating the login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        // Setting up the mock user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword"); // This should be the encoded password
        user.setVerify(1);

        // Setting up the mock UserDetails
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(user.getEmail());
        when(userDetails.getPassword()).thenReturn("encodedPassword"); // Ensure this matches the encoded password

        // Mocking the repository and service calls
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(customUserDetails.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true); // Ensure this returns true
        when(jwtTokenProvider.generateJwtToken(any(Authentication.class))).thenReturn("jwtToken");

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<AuthResponse> response = authController.signin(loginRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("jwtToken", response.getBody().getJwt());
    }

    @Test
    void deactivateUser() throws MessagingException, jakarta.mail.MessagingException, UserException {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail("test@example.com");

        doNothing().when(userService).deactivateUser(anyString());

        ResponseEntity<AuthResponse> response = authController.deactivateUser(otpRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("User deactivated successfully.", response.getBody().getMessage());
    }

    @Test
    void reactivateUser() throws UserException {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail("test@example.com");
        otpRequest.setOtp("123456");

        doNothing().when(userService).reactivateUser(anyString(), anyString());

        ResponseEntity<AuthResponse> response = authController.reactivateUser(otpRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("User reactivated successfully.", response.getBody().getMessage());
    }

    @Test
    void requestReactivateOtp() throws MessagingException, jakarta.mail.MessagingException {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail("test@example.com");

        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<AuthResponse> response = authController.requestReactivateOtp(otpRequest);

        verify(emailService, times(1)).sendReactivateOtp(anyString(), anyString());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("OTP sent to email for reactivation.", response.getBody().getMessage());
    }

    @Test
    void forgetPassword() throws MessagingException, jakarta.mail.MessagingException, UserException {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail("test@example.com");

        doNothing().when(userService).forgetPassword(anyString());

        ResponseEntity<AuthResponse> response = authController.forgetPassword(otpRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("OTP sent to email.", response.getBody().getMessage());
    }

    @Test
    void resetPassword() throws UserException {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("test@example.com");
        resetPasswordRequest.setOtp("123456");
        resetPasswordRequest.setNewPassword("newPassword");

        doNothing().when(userService).resetPassword(anyString(), anyString(), anyString());

        ResponseEntity<AuthResponse> response = authController.resetPassword(resetPasswordRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getStatus());
        assertEquals("Password reset successfully.", response.getBody().getMessage());
    }

    @Test
    void updateUserHandler() throws UserException {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

        when(userService.updateUser(anyInt(), any(UpdateUserRequest.class))).thenReturn(user);

        ResponseEntity<UserDto> response = authController.updateUserHandler(updateUserRequest, 1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test@example.com", response.getBody().getEmail());
    }
}