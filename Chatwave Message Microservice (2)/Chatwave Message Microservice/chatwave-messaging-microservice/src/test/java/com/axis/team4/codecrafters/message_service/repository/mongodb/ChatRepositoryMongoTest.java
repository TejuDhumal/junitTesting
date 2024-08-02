package com.axis.team4.codecrafters.message_service.repository.mongodb;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.message_service.modal.Chat;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class ChatRepositoryMongoTest {

    @Mock
    private ChatRepositoryMongo chatRepository;

    private Chat chat1;
    private Chat chat2;

    @BeforeEach
    public void setUp() {
        chat1 = new Chat();
        chat1.setId(1);
        chat1.setChatName("Chat 1");

        chat2 = new Chat();
        chat2.setId(2);
        chat2.setChatName("Chat 2");
    }

    @Test
    public void testFindById() {
        when(chatRepository.findById(any(Integer.class))).thenReturn(Optional.of(chat1));

        Optional<Chat> foundChat = chatRepository.findById(1);

        assertTrue(foundChat.isPresent());
        assertEquals("Chat 1", foundChat.get().getChatName());
    }

    @Test
    public void testSave() {
        when(chatRepository.save(any(Chat.class))).thenReturn(chat1);

        Chat savedChat = chatRepository.save(chat1);

        assertNotNull(savedChat);
        assertEquals("Chat 1", savedChat.getChatName());
    }

    @Test
    public void testFindAll() {
        when(chatRepository.findAll()).thenReturn(Arrays.asList(chat1, chat2));

        List<Chat> chats = chatRepository.findAll();

        assertNotNull(chats);
        assertEquals(2, chats.size());
        assertEquals("Chat 1", chats.get(0).getChatName());
        assertEquals("Chat 2", chats.get(1).getChatName());
    }

    @Test
    public void testDeleteById() {
        chatRepository.deleteById(1);
        when(chatRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Chat> deletedChat = chatRepository.findById(1);

        assertTrue(deletedChat.isEmpty());
    }
}
