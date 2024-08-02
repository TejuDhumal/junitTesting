package com.axis.team4.codecrafters.chat_history_service.modal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

    private Message message;
    private User user;
    private Chat chat;

    @BeforeEach
    public void setUp() {
        user = new User(); // Assuming a default constructor exists
        chat = new Chat(); // Assuming a default constructor exists
        message = new Message(1, "Hello, World!", LocalDateTime.now(), true, user, chat, false);
    }

    @Test
    public void testMessageEntity() {
        // Test getters
        assertEquals(1, message.getId());
        assertEquals("Hello, World!", message.getContent());
        assertEquals(true, message.getIs_read());
        assertEquals(user, message.getUser());
        assertEquals(chat, message.getChat());
        assertEquals(false, message.getIsAttachment());

        // Test setters
        message.setContent("Updated Content");
        assertEquals("Updated Content", message.getContent());
    }
}
