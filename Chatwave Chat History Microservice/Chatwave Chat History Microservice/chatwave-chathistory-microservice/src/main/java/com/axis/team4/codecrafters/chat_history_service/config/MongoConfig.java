// Purpose: Configuration class for MongoDB. It creates a MongoClient and MongoTemplate bean for MongoDB connection.

package com.axis.team4.codecrafters.chat_history_service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.axis.team4.codecrafters.message_service.repository.mongodb",
    mongoTemplateRef = "mongoTemplate"
)
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://admin1234:admin1234@chatwave.pjukea4.mongodb.net/chatwave?retryWrites=true&w=majority");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "chatwave");
    }
}
