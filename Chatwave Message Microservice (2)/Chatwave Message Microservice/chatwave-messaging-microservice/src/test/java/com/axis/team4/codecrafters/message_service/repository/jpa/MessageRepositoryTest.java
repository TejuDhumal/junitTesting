package com.axis.team4.codecrafters.message_service.repository.jpa;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.Message;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class MessageRepositoryTest {

    @Mock
    private MessageRepository messageRepository;

    private Chat chat;
    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {
        chat = new Chat();
        chat.setId(1);

        message1 = new Message();
        message1.setId(1);
        message1.setChat(chat);
        message1.setContent("Hello");

        message2 = new Message();
        message2.setId(2);
        message2.setChat(chat);
        message2.setContent("Hi there");
    }

    @Test
    public void testFindMessageByChatId() {
        when(messageRepository.findMessageByChatId(anyInt())).thenReturn(Arrays.asList(message1, message2));

        List<Message> messages = messageRepository.findMessageByChatId(1);

        assertNotNull(messages);
        assertEquals(2, messages.size());
        assertEquals("Hello", messages.get(0).getContent());
        assertEquals("Hi there", messages.get(1).getContent());
    }
}
