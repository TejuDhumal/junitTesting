package com.axis.team4.codecrafters.message_service.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.axis.team4.codecrafters.message_service.modal.User;

import jakarta.persistence.Column;

public class ChatDto {
    private Integer id;
    private String chatName;
    private String chatImage;
    private Boolean blocked = false;
    private Integer blockedBy;
    @Column(columnDefinition = "boolean default false")
    private Boolean isGroup;
    private Set<UserDto> admins = new HashSet<>();
    private UserDto createdBy;
    private Set<UserDto> users = new HashSet<>();
    private List<MessageDto> messages = new ArrayList<>();
    private User user = new User();

    // Private constructor to enforce the use of the builder
    private ChatDto(Builder builder) {
        this.id = builder.id;
        this.chatName = builder.chatName;
        this.chatImage = builder.chatImage;
        this.blocked = builder.blocked;
        this.blockedBy = builder.blockedBy;
        this.isGroup = builder.isGroup;
        this.admins = builder.admins;
        this.createdBy = builder.createdBy;
        this.users = builder.users;
        this.messages = builder.messages;
        this.user = builder.user;
    }

    public ChatDto() {
		
	}

	public static class Builder {
        private Integer id;
        private String chatName;
        private String chatImage;
        private Boolean blocked = false;
        private Integer blockedBy;
        private Boolean isGroup;
        private Set<UserDto> admins = new HashSet<>();
        private UserDto createdBy;
        private Set<UserDto> users = new HashSet<>();
        private List<MessageDto> messages = new ArrayList<>();
        private User user = new User();

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder chatName(String chatName) {
            this.chatName = chatName;
            return this;
        }

        public Builder chatImage(String chatImage) {
            this.chatImage = chatImage;
            return this;
        }

        public Builder blocked(Boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public Builder blockedBy(Integer blockedBy) {
            this.blockedBy = blockedBy;
            return this;
        }

        public Builder isGroup(Boolean isGroup) {
            this.isGroup = isGroup;
            return this;
        }

        public Builder admins(Set<UserDto> admins) {
            this.admins = admins;
            return this;
        }

        public Builder createdBy(UserDto createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder users(Set<UserDto> users) {
            this.users = users;
            return this;
        }

        public Builder messages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public ChatDto build() {
            return new ChatDto(this);
        }
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(Integer blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Set<UserDto> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<UserDto> admins) {
        this.admins = admins;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
