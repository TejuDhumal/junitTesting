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
import com.axis.team4.codecrafters.message_service.exception.UserException;
import com.axis.team4.codecrafters.message_service.modal.Chat;
import com.axis.team4.codecrafters.message_service.modal.User;
import com.axis.team4.codecrafters.message_service.repository.jpa.ChatRepository;
import com.axis.team4.codecrafters.message_service.repository.jpa.UserRepository;
import com.axis.team4.codecrafters.message_service.repository.mongodb.ChatRepositoryMongo;
import com.axis.team4.codecrafters.message_service.request.GroupChatRequest;

//@Disabled
class ChatServiceImplementationTest {

    @Mock
    private UserService userService;

    @Mock
    private ChatRepository chatRepo;

    @Mock
    private ChatRepositoryMongo chatRepositoryMongo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private ChatServiceImplementation chatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChat() throws UserException {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);

        when(userService.findUserById(1)).thenReturn(user1);
        when(userService.findUserById(2)).thenReturn(user2);
        when(chatRepo.findSingleChatByUsersId(user2, user1)).thenReturn(null);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat createdChat = chatService.createChat(1, 2, false);

        assertNotNull(createdChat);
        assertEquals(2, createdChat.getUsers().size());
        verify(chatRepo, times(1)).save(createdChat);
        verify(chatRepositoryMongo, times(1)).save(createdChat);
    }

    @Test
    void testFindChatById() throws ChatException {
        Chat chat = new Chat();
        chat.setId(1);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));

        Chat foundChat = chatService.findChatById(1);

        assertNotNull(foundChat);
        assertEquals(1, foundChat.getId());
    }

    @Test
    void testFindChatById_NotFound() {
        when(chatRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ChatException.class, () -> chatService.findChatById(1));
    }

    @Test
    void testFindAllChatByUserId() throws UserException {
        User user = new User();
        user.setId(1);

        List<Chat> chatList = new ArrayList<>();
        chatList.add(new Chat());

        when(userService.findUserById(1)).thenReturn(user);
        when(chatRepo.findChatByUserId(1)).thenReturn(chatList);

        List<Chat> foundChats = chatService.findAllChatByUserId(1);

        assertNotNull(foundChats);
        assertEquals(1, foundChats.size());
    }

    @Test
    void testDeleteChat() throws UserException, ChatException {
        User user = new User();
        user.setId(1);

        Chat chat = new Chat();
        chat.setId(1);
        chat.setCreatedBy(user);
        chat.setIsGroup(false);

        when(userService.findUserById(1)).thenReturn(user);
        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));

        Chat deletedChat = chatService.deleteChat(1, 1);

        assertNotNull(deletedChat);
        verify(chatRepo, times(1)).deleteById(1);
        verify(chatRepositoryMongo, times(1)).deleteById(1);
    }

    @Test
    void testCreateGroup() throws UserException {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);

        GroupChatRequest request = new GroupChatRequest();
        request.setUserIds(List.of(2));
        request.setChat_name("Group Chat");
        request.setChat_image("group.jpg");

        when(userService.findUserById(1)).thenReturn(user1);
        when(userService.findUserById(2)).thenReturn(user2);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat createdGroup = chatService.createGroup(request, 1);

        assertNotNull(createdGroup);
        assertEquals(2, createdGroup.getUsers().size());
        assertTrue(createdGroup.getIsGroup());
        verify(chatRepo, times(1)).save(createdGroup);
        verify(chatRepositoryMongo, times(1)).save(createdGroup);
    }

    @Test
    void testAddUsersToGroup() throws UserException, ChatException {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getUsers().add(user1);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(2)).thenReturn(user2);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat updatedChat = chatService.addUsersToGroup(List.of(2), 1);

        assertNotNull(updatedChat);
        assertEquals(2, updatedChat.getUsers().size());
        verify(chatRepo, times(1)).save(updatedChat);
        verify(chatRepositoryMongo, times(1)).save(updatedChat);
    }

    @Test
    void testRenameGroup() throws UserException, ChatException {
        User admin = new User();
        admin.setId(1);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getAdmins().add(admin);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(1)).thenReturn(admin);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat renamedChat = chatService.renameGroup(1, "New Group Name", "new_image.jpg", 1);

        assertNotNull(renamedChat);
        assertEquals("New Group Name", renamedChat.getChatName());
        assertEquals("new_image.jpg", renamedChat.getChatImage());
        verify(chatRepo, times(1)).save(renamedChat);
        verify(chatRepositoryMongo, times(1)).save(renamedChat);
    }

    @Test
    void testRemoveFromGroup() throws UserException, ChatException {
        User admin = new User();
        admin.setId(1);
        User user = new User();
        user.setId(2);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getAdmins().add(admin);
        chat.getUsers().add(user);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(1)).thenReturn(admin);
        when(userService.findUserById(2)).thenReturn(user);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat updatedChat = chatService.removeFromGroup(1, 2, 1);

        assertNotNull(updatedChat);
        assertFalse(updatedChat.getUsers().contains(user));
        verify(chatRepo, times(1)).save(updatedChat);
        verify(chatRepositoryMongo, times(1)).save(updatedChat);
    }

    @Test
    void testExitFromGroup() throws UserException, ChatException {
        User user = new User();
        user.setId(1);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getUsers().add(user);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(1)).thenReturn(user);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat updatedChat = chatService.exitFromGroup(1, 1);

        assertNotNull(updatedChat);
        assertFalse(updatedChat.getUsers().contains(user));
        verify(chatRepo, times(1)).save(updatedChat);
        verify(chatRepositoryMongo, times(1)).save(updatedChat);
    }

    @Test
    void testBlockChat() throws UserException, ChatException {
        User user = new User();
        user.setId(1);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getUsers().add(user);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(1)).thenReturn(user);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat blockedChat = chatService.blockChat(1, 1);

        assertNotNull(blockedChat);
        assertTrue(blockedChat.isBlocked());
        assertEquals(1, blockedChat.getBlockedBy());
        verify(chatRepo, times(1)).save(blockedChat);
        verify(chatRepositoryMongo, times(1)).save(blockedChat);
    }

    @Test
    void testUnblockChat() throws UserException, ChatException {
        User user = new User();
        user.setId(1);

        Chat chat = new Chat();
        chat.setId(1);
        chat.getUsers().add(user);
        chat.setBlocked(true);
        chat.setBlockedBy(1);

        when(chatRepo.findById(1)).thenReturn(Optional.of(chat));
        when(userService.findUserById(1)).thenReturn(user);
        when(chatRepo.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat unblockedChat = chatService.unblockChat(1, 1);

        assertNotNull(unblockedChat);
        assertFalse(unblockedChat.isBlocked());
        assertNull(unblockedChat.getBlockedBy());
        verify(chatRepo, times(1)).save(unblockedChat);
        verify(chatRepositoryMongo, times(1)).save(unblockedChat);
    }
}
