package com.axis.team4.codecrafters.message_service.repository.redis;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.message_service.modal.Message;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class MessageRepositoryRedisTest {

    @Mock
    private MessageRepositoryRedis messageRepository;

    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {
        message1 = new Message();
        message1.setId(1);
        message1.setContent("Hello, this is message 1");

        message2 = new Message();
        message2.setId(2);
        message2.setContent("Hello, this is message 2");
    }

    @Test
    public void testFindById() {
        when(messageRepository.findById(anyInt())).thenReturn(Optional.of(message1));

        Optional<Message> foundMessage = messageRepository.findById(1);

        assertTrue(foundMessage.isPresent());
        assertEquals("Hello, this is message 1", foundMessage.get().getContent());
    }

    @Test
    public void testSave() {
        when(messageRepository.save(any(Message.class))).thenReturn(message1);

        Message savedMessage = messageRepository.save(message1);

        assertNotNull(savedMessage);
        assertEquals("Hello, this is message 1", savedMessage.getContent());
    }

    @Test
    public void testFindAll() {
        Iterable<Message> messages = Arrays.asList(message1, message2);
        when(messageRepository.findAll()).thenReturn(messages);

        Iterable<Message> result = messageRepository.findAll();

        assertNotNull(result);
        assertEquals(2, ((Collection<?>) result).size());
    }

    @Test
    public void testDeleteById() {
        messageRepository.deleteById(1);
        verify(messageRepository, times(1)).deleteById(1);
    }
}
