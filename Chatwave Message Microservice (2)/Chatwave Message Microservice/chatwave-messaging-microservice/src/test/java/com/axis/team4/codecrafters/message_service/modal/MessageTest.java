package com.axis.team4.codecrafters.message_service.modal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//@Disabled
public class MessageTest {

    @Mock
    private User userMock;

    @Mock
    private Chat chatMock;

    @InjectMocks
    private Message message;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDefaultConstructor() {
        Message msg = new Message();
        assertNotNull(msg);
    }

    @Test
    public void testParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Message msg = new Message(1, "Hello", now, true, userMock, chatMock, false, "123");
        assertNotNull(msg);
        assertEquals(1, msg.getId());
        assertEquals("Hello", msg.getContent());
        assertEquals(now, msg.getTimeStamp());
        assertTrue(msg.getIsRead());
        assertFalse(msg.getIsAttachment());
        assertEquals(userMock, msg.getUser());
        assertEquals(chatMock, msg.getChat());
    }

    @Test
    public void testGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();
        message.setId(1);
        message.setContent("Hello");
        message.setTimeStamp(now);
        message.setIsRead(true);
        message.setIsAttachment(false);
        message.setUser(userMock);
        message.setChat(chatMock);

        assertEquals(1, message.getId());
        assertEquals("Hello", message.getContent());
        assertEquals(now, message.getTimeStamp());
        assertTrue(message.getIsRead());
        assertFalse(message.getIsAttachment());
        assertEquals(userMock, message.getUser());
        assertEquals(chatMock, message.getChat());
    }

    @Test
    public void testChatId() {
        when(chatMock.getId()).thenReturn(123);
        message.setChat(chatMock);
        assertEquals("123", message.getChatId());
    }
}
