// User DTO class

package com.axis.team4.codecrafters.user_service.dto;

import java.util.Objects;

public class UserDto {
	
	private Integer id;
	private String fullName;
	private String username;
	private String email;
	private String profilePicture;
	private String bio;
	
	
	public UserDto() {
		
	}

//Constructor for UserDto
	
	public UserDto(Integer id, String fullName, String username, String email, String profilePicture, String bio) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.profilePicture = profilePicture;
		this.bio = bio;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfullName() {
		return fullName;
	}

	public void setfullName(String full_name) {
		this.fullName = full_name;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getprofilePicture() {
		return profilePicture;
	}

	public void setprofilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, username, id, profilePicture, bio);
	}

	// Overriding equals method
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && Objects.equals(username, other.username)
				&& Objects.equals(id, other.id) && Objects.equals(profilePicture, other.profilePicture) && Objects.equals(bio, other.bio);
	}
}
