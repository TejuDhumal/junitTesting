// Purpose: UserDto class to store user data.

package com.axis.team4.codecrafters.message_service.dto;

import java.util.Objects;

public class UserDto {
	
	private Integer id;
	private String fullName;
	private String email;
	private String profilePicture;
	private String username;
	private String bio;
	
	
	public UserDto() {
	}
	
	
	
	public UserDto(Integer id, String fullName, String email, String profilePicture, String username, String bio) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.profilePicture = profilePicture;
		this.username = username;
		this.bio = bio;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getProfilePicture() {
		return profilePicture;
	}



	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, id, profilePicture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(profilePicture, other.profilePicture);
	}
	
	

}
