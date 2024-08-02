package com.axis.team4.codecrafters.message_service.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String chatName;
    private String chatImage;
    @Column(name = "blocked", nullable = false)
    private boolean blocked = false;
    private Integer blockedBy;

    @ManyToMany
    private Set<User> admins = new HashSet<>();

    private Boolean isGroup;

    @ManyToOne
    private User createdBy;

    @ManyToMany
    @JoinTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    private Chat(ChatBuilder builder) {
        this.id = builder.id;
        this.chatName = builder.chatName;
        this.chatImage = builder.chatImage;
        this.blocked = builder.blocked;
        this.blockedBy = builder.blockedBy;
        this.admins = builder.admins;
        this.isGroup = builder.isGroup;
        this.createdBy = builder.createdBy;
        this.users = builder.users;
        this.messages = builder.messages;
    }

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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(Integer blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static class ChatBuilder {
        private Integer id;
        private String chatName;
        private String chatImage;
        private boolean blocked = false;
        private Integer blockedBy;
        private Set<User> admins = new HashSet<>();
        private Boolean isGroup;
        private User createdBy;
        private Set<User> users = new HashSet<>();
        private List<Message> messages = new ArrayList<>();

        public ChatBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ChatBuilder setChatName(String chatName) {
            this.chatName = chatName;
            return this;
        }

        public ChatBuilder setChatImage(String chatImage) {
            this.chatImage = chatImage;
            return this;
        }

        public ChatBuilder setBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public ChatBuilder setBlockedBy(Integer blockedBy) {
            this.blockedBy = blockedBy;
            return this;
        }

        public ChatBuilder setAdmins(Set<User> admins) {
            this.admins = admins;
            return this;
        }

        public ChatBuilder setIsGroup(Boolean isGroup) {
            this.isGroup = isGroup;
            return this;
        }

        public ChatBuilder setCreatedBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ChatBuilder setUsers(Set<User> users) {
            this.users = users;
            return this;
        }

        public ChatBuilder setMessages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }
}
