package com.axis.team4.codecrafters.chat_history_service.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import com.axis.team4.codecrafters.chat_history_service.modal.User;
import com.axis.team4.codecrafters.chat_history_service.repository.ChatHistoryRepository;

@ExtendWith(MockitoExtension.class)
public class ChatHistoryServiceImplementationTest {

    @Mock
    private ChatHistoryRepository chatHistoryRepository;

    @InjectMocks
    private ChatHistoryServiceImplementation chatHistoryServiceImplementation;

    private Chat chat;
    private List<Chat> chatList;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "John Doe", "john.doe@example.com", "profile_pic.jpg", "password123", new ArrayList<>());
        Set<User> users = new HashSet<>();
        users.add(user);
        chat = new Chat(1, "Test Chat", "test_image.png", new HashSet<>(), false, user, users, new ArrayList<>());
        chatList = new ArrayList<>();
        chatList.add(chat);
    }

    @Test
    public void testGetAllChats() {
        when(chatHistoryRepository.findAll()).thenReturn(chatList);
        List<Chat> result = chatHistoryServiceImplementation.getAllChats();
        assertEquals(1, result.size());
        assertEquals(chat.getId(), result.get(0).getId());
    }

    @Test
    public void testGetChatById() {
        when(chatHistoryRepository.findById(any(Integer.class))).thenReturn(Optional.of(chat));
        Chat result = chatHistoryServiceImplementation.getChatById(1);
        assertEquals(chat.getId(), result.getId());
    }

    @Test
    public void testGetChatById_NotFound() {
        when(chatHistoryRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        Chat result = chatHistoryServiceImplementation.getChatById(1);
        assertNull(result);
    }

    @Test
    public void testGetChatsByUserId() {
        when(chatHistoryRepository.findAll()).thenReturn(chatList);
        List<Chat> result = chatHistoryServiceImplementation.getChatsByUserId(1);
        assertEquals(1, result.size());
        assertEquals(chat.getId(), result.get(0).getId());
    }

    @Test
    public void testGetChatsByUserId_NoChats() {
        when(chatHistoryRepository.findAll()).thenReturn(new ArrayList<>());
        List<Chat> result = chatHistoryServiceImplementation.getChatsByUserId(1);
        assertEquals(0, result.size());
    }
}

