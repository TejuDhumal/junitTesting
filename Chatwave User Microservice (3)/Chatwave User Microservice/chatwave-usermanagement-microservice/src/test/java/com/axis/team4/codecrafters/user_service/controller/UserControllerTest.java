package com.axis.team4.codecrafters.user_service.controller;

import com.axis.team4.codecrafters.user_service.controller.mapper.UserDtoMapper;
import com.axis.team4.codecrafters.user_service.dto.UserDto;
import com.axis.team4.codecrafters.user_service.exception.UserException;
import com.axis.team4.codecrafters.user_service.modal.User;
import com.axis.team4.codecrafters.user_service.request.UpdateUserRequest;
import com.axis.team4.codecrafters.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@Disabled
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUserHandler() throws UserException {
        // Arrange
        Integer userId = 1;
        UpdateUserRequest request = new UpdateUserRequest();
        User updatedUser = new User();
        UserDto updatedUserDto = new UserDto();

        when(userService.updateUser(userId, request)).thenReturn(updatedUser);

        // Act
        ResponseEntity<UserDto> response = userController.updateUserHandler(request, userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserDto, response.getBody());
    }

    @Test
    void getUserProfileHandler() throws UserException {
        // Arrange
        String jwt = "Bearer token";
        User user = new User();
        UserDto userDto = new UserDto();

        when(userService.findUserProfile(jwt)).thenReturn(user);

        // Act
        ResponseEntity<UserDto> response = userController.getUserProfileHandler(jwt);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void searchUsersByName() {
        // Arrange
        String name = "John";
        User user1 = new User();
        User user2 = new User();
        user1.setFullName(name);
        List<User> users = Arrays.asList(user1, user2);

        when(userService.searchUser(name)).thenReturn(users);

        // Act
        ResponseEntity response = userController.searchUsersByName(name);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}
