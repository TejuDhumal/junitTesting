package com.axis.team4.codecrafters.chat_history_service.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChatTest {

    private Chat chat;

    @BeforeEach
    public void setUp() {
        chat = new Chat(1, "Test Chat", "image.png", new HashSet<>(), false, new User(), new HashSet<>(), List.of());
    }

    @Test
    public void testChatEntity() {
        // Test getters
        assertEquals(1, chat.getId());
        assertEquals("Test Chat", chat.getChat_name());
        assertEquals("image.png", chat.getChat_image());
        assertEquals(false, chat.getIs_group());

        // Test setters
        chat.setChat_name("Updated Chat");
        assertEquals("Updated Chat", chat.getChat_name());
    }
}

