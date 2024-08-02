package com.axis.team4.codecrafters.chat_history_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import com.axis.team4.codecrafters.chat_history_service.service.ChatHistoryService;

@ExtendWith(MockitoExtension.class)
public class ChatHistoryServiceTest {

    @Mock
    private ChatHistoryRepository chatHistoryRepository;

    @InjectMocks
    private ChatHistoryService chatHistoryService;

    private Chat chat;
    private List<Chat> chatList;

    @BeforeEach
    public void setUp() {
        chat = new Chat(1, "Test Chat", "test_image.png");
        chatList = new ArrayList<>();
        chatList.add(chat);
    }
//
//    @Test
//    public void testGetAllChats() {
//        when(chatHistoryRepository.findAll()).thenReturn(chatList);
//        List<Chat> result = chatHistoryService.getAllChats();
//        assertEquals(1, result.size());
//        assertEquals(chat.getId(), result.get(0).getId());
//    }
//
//    @Test
//    public void testGetChatById() {
//        when(chatHistoryRepository.findById(any(Integer.class))).thenReturn(Optional.of(chat));
//        Optional<Chat> result = Optional.ofNullable(chatHistoryService.getChatById(1));
//        assertTrue(result.isPresent());
//        assertEquals(chat.getId(), result.get().getId());
//    }

  

}
