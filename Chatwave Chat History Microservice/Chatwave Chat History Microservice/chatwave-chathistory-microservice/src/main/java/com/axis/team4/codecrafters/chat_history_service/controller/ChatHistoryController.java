// Purpose: Contains the REST API endpoints for the chat history service.

package com.axis.team4.codecrafters.chat_history_service.controller;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import com.axis.team4.codecrafters.chat_history_service.service.ChatHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat-history")
public class ChatHistoryController {
	
	private final ChatHistoryService chatHistoryService;

    public ChatHistoryController(ChatHistoryService chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }
  
    // REST API endpoints for the chat history service

    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> chats = chatHistoryService.getAllChats();
        return ResponseEntity.ok(chats);
    }
    
    // Get chat by chatId

    @GetMapping("/chats/{chatId}")
    public ResponseEntity<Chat> getChatById(@PathVariable Integer chatId) {
        Chat chat = chatHistoryService.getChatById(chatId);
        if (chat != null) {
            return ResponseEntity.ok(chat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get chats by userId

    @GetMapping("/user/{userId}/chats")
    public ResponseEntity<List<Chat>> getChatsByUserId(@PathVariable Integer userId) {
        List<Chat> chats = chatHistoryService.getChatsByUserId(userId);
        return ResponseEntity.ok(chats);
    }
}
