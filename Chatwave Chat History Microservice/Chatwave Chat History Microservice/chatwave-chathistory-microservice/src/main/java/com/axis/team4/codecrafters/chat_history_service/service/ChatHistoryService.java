package com.axis.team4.codecrafters.chat_history_service.service;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import java.util.List;

public interface ChatHistoryService {
    List<Chat> getAllChats();
    Chat getChatById(Integer chatId);
    List<Chat> getChatsByUserId(Integer userId);
}
