// Purpose: Main class for the chat history service. It is responsible for starting the application.

package com.axis.team4.codecrafters.chat_history_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.axis.team4.codecrafters.chat_history_service.repository")
@EnableDiscoveryClient
public class ChatHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatHistoryServiceApplication.class, args);
    }
}
