package com.axis.team4.codecrafters.message_service.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.Message;
import com.axis.team4.codecrafters.message_service.modal.User;
import com.axis.team4.codecrafters.message_service.request.SendMessageRequest;
import com.axis.team4.codecrafters.message_service.service.ChatService;
import com.axis.team4.codecrafters.message_service.service.MessageService;
import com.axis.team4.codecrafters.message_service.service.UserService;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class RealTimeChatTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private UserService userService;

    @Mock
    private MessageService messageService;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private RealTimeChat realTimeChat;

    private Message message;
    private SendMessageRequest sendMessageRequest;
    private User user;
    private Chat chat;

    @BeforeEach
    public void setUp() {
        message = new Message();
        message.setId(1);
        message.setContent("Test message");

        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

        chat = new Chat();
        chat.setId(1);
        chat.setChatName("Test Chat");

        sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setChatId(1);
        sendMessageRequest.setContent("Test message");
    }

    @Test
    public void testReceiveMessage() {
        Message response = realTimeChat.receiveMessage(message);
        assertEquals(message, response);
    }

    
   
}
