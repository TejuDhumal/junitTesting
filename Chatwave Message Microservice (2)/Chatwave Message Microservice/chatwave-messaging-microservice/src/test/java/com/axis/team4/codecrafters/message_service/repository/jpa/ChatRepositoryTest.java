package com.axis.team4.codecrafters.message_service.repository.jpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.User;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class ChatRepositoryTest {

    @Mock
    private ChatRepository chatRepository;

    private User user1;
    private User user2;
    private Chat chat1;
    private Chat chat2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setId(1);
        user2 = new User();
        user2.setId(2);

        chat1 = new Chat();
        chat1.setId(1);
        chat1.setUsers(new HashSet<>(Arrays.asList(user1, user2)));
        chat1.setIsGroup(false);

        chat2 = new Chat();
        chat2.setId(2);
        chat2.setUsers(new HashSet<>(Arrays.asList(user1)));
        chat2.setIsGroup(true);
    }

    @Test
    public void testFindChatByUserId() {
        when(chatRepository.findChatByUserId(anyInt())).thenReturn(Arrays.asList(chat1, chat2));

        List<Chat> chats = chatRepository.findChatByUserId(1);

        assertNotNull(chats);
        assertEquals(2, chats.size());
    }

    @Test
    public void testFindSingleChatByUsersId() {
        when(chatRepository.findSingleChatByUsersId(any(User.class), any(User.class))).thenReturn(chat1);

        Chat chat = chatRepository.findSingleChatByUsersId(user1, user2);

        assertNotNull(chat);
        assertEquals(1, chat.getId());
        assertEquals(false, chat.getIsGroup());
    }
}
