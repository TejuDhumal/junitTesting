package com.axis.team4.codecrafters.message_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.axis.team4.codecrafters.message_service.exception.ChatException;
import com.axis.team4.codecrafters.message_service.exception.MessageException;
import com.axis.team4.codecrafters.message_service.exception.UserException;
import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.Message;
import com.axis.team4.codecrafters.message_service.modal.User;
import com.axis.team4.codecrafters.message_service.repository.jpa.MessageRepository;
import com.axis.team4.codecrafters.message_service.repository.redis.MessageRepositoryRedis;
import com.axis.team4.codecrafters.message_service.request.SendMessageRequest;

//@Disabled
class MessageServiceImplementTest {

    @Mock
    private MessageRepository messageRepo;

    @Mock
    private MessageRepositoryRedis messageRepositoryRedis;

    @Mock
    private UserService userService;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private MessageServiceImplement messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() throws UserException, ChatException {
        User user = new User();
        user.setId(1);
        Chat chat = new Chat();
        chat.setId(1);
        SendMessageRequest req = new SendMessageRequest();
        req.setUserId(1);
        req.setChatId(1);
        req.setContent("Hello!");

        when(userService.findUserById(1)).thenReturn(user);
        when(chatService.findChatById(1)).thenReturn(chat);
        when(messageRepo.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Message sentMessage = messageService.sendMessage(req);

        assertNotNull(sentMessage);
        assertEquals("Hello!", sentMessage.getContent());
        assertEquals(user, sentMessage.getUser());
        assertEquals(chat, sentMessage.getChat());
        verify(messageRepo, times(1)).save(sentMessage);
        verify(messageRepositoryRedis, times(1)).save(sentMessage);
    }

    @Test
    void testEditMessage() throws MessageException {
        Message message = new Message();
        message.setId(1);
        message.setContent("Old Content");

        when(messageRepo.findById(1)).thenReturn(Optional.of(message));
        when(messageRepo.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Message editedMessage = messageService.editMessage(1, "New Content");

        assertNotNull(editedMessage);
        assertEquals("New Content", editedMessage.getContent());
        verify(messageRepo, times(1)).save(editedMessage);
        verify(messageRepositoryRedis, times(1)).save(editedMessage);
    }

    @Test
    void testDeleteMessage() throws MessageException {
        Message message = new Message();
        message.setId(1);
        message.setContent("Some Content");

        when(messageRepo.findById(1)).thenReturn(Optional.of(message));
        when(messageRepo.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String result = messageService.deleteMessage(1);

        assertEquals("message content updated to 'message deleted'", result);
        assertEquals("This message was deleted", message.getContent());
        verify(messageRepo, times(1)).save(message);
        verify(messageRepositoryRedis, times(1)).save(message);
    }

    @Test
    void testGetChatsMessages() throws ChatException {
        Chat chat = new Chat();
        chat.setId(1);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());

        when(chatService.findChatById(1)).thenReturn(chat);
        when(messageRepo.findMessageByChatId(1)).thenReturn(messages);

        List<Message> retrievedMessages = messageService.getChatsMessages(1);

        assertNotNull(retrievedMessages);
        assertEquals(1, retrievedMessages.size());
        verify(messageRepo, times(1)).findMessageByChatId(1);
    }

    @Test
    void testFindMessageById() throws MessageException {
        Message message = new Message();
        message.setId(1);

        when(messageRepo.findById(1)).thenReturn(Optional.of(message));

        Message foundMessage = messageService.findMessageById(1);

        assertNotNull(foundMessage);
        assertEquals(1, foundMessage.getId());
    }

    @Test
    void testFindMessageById_NotFound() {
        when(messageRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(MessageException.class, () -> messageService.findMessageById(1));
    }
}
