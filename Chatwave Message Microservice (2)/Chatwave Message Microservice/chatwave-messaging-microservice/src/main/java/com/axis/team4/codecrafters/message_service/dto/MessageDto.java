// Purpose: MessageDto class for message

package com.axis.team4.codecrafters.message_service.dto;

import java.time.LocalDateTime;

public class MessageDto {

private String content;
	

	private Integer id;
	private LocalDateTime timeStamp;
	private Boolean isRead;
	private UserDto user;
	private ChatDto chat;
	private Boolean isAttachment;
	
	public MessageDto() {
		
	}
	
	public MessageDto(String content, Integer id, LocalDateTime timeStamp, Boolean isRead, UserDto user,
			ChatDto chat, Boolean isAttachment) {
		super();
		this.content = content;
		this.id = id;
		this.timeStamp = timeStamp;
		this.isRead = isRead;
		this.user = user;
		this.chat = chat;
		this.setIsAttachment(isAttachment);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIs_read(Boolean isRead) {
		this.isRead = isRead;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public ChatDto getChat() {
		return chat;
	}
	public void setChat(ChatDto chat) {
		this.chat = chat;
	}

	public Boolean getIsAttachment() {
		return isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

}