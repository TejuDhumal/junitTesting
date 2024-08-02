package com.axis.team4.codecrafters.chat_history_service.repository;

import com.axis.team4.codecrafters.chat_history_service.modal.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatHistoryRepository extends MongoRepository<Chat, Integer> {
}
