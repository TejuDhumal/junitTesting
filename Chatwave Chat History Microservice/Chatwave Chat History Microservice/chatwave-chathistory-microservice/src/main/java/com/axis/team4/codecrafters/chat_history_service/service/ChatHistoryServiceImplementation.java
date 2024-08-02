package com.axis.team4.codecrafters.chat_history_service.service;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import com.axis.team4.codecrafters.chat_history_service.repository.ChatHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryServiceImplementation implements ChatHistoryService {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatHistoryRepository.findAll();
    }

    @Override
    public Chat getChatById(Integer chatId) {
        return chatHistoryRepository.findById(chatId).orElse(null);
    }

    @Override
    public List<Chat> getChatsByUserId(Integer userId) {
        return chatHistoryRepository.findAll().stream()
                .filter(chat -> chat.getUsers().stream().anyMatch(user -> user.getId().equals(userId)))
                .toList();
    }
}
