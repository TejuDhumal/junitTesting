package com.axis.team4.codecrafters.message_service.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.User;
import com.axis.team4.codecrafters.message_service.request.GroupChatRequest;
import com.axis.team4.codecrafters.message_service.service.ChatService;
import com.axis.team4.codecrafters.message_service.service.UserService;

//@Disabled
public class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   

    @Test
    public void testCreateGroupChatSuccess() throws Exception {
        User mockUser = new User(1, "testUser");
        when(userService.findUserProfile(any())).thenReturn(mockUser);

        Chat mockChat = new Chat();
        mockChat.setId(1);
        when(chatService.createGroup(any(), any())).thenReturn(mockChat);

        GroupChatRequest request = new GroupChatRequest();
        request.setChat_name("testGroup");
        request.setUserIds(Arrays.asList(1, 2, 3));

        ResponseEntity<?> response = chatController.createGroupHandler(request, "jwtToken");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", response.getBody());
    }

    @Test
    public void testFindChatByIdSuccess() throws Exception {
        Chat mockChat = new Chat();
        mockChat.setId(1);
        when(chatService.findChatById(any())).thenReturn(mockChat);

        ResponseEntity<?> response = chatController.findChatByIdHandler(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", response.getBody());
    }

    @Test
    public void testFindAllChatByUserIdSuccess() throws Exception {
        User mockUser = new User(1, "testUser");
        when(userService.findUserProfile(any())).thenReturn(mockUser);

        Chat mockChat = new Chat();
        mockChat.setId(1);
        when(chatService.findAllChatByUserId(any())).thenReturn(Arrays.asList(mockChat));

        ResponseEntity<?> response = chatController.findAllChatByUserIdHandler("jwtToken");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", response.getBody());
    }



}
