// User Modal class for User Service

package com.axis.team4.codecrafters.user_service.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fullName;
	private String username;
	private String email;
	private String profilePicture;
	private String password;
    private int isVerify = 0;
    private String otp;
    private String bio;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Notification> notifications = new ArrayList<>();
	
	public User() {
		// Default constructor
	}
	
	public User(Integer id, String fullName, String username,String email, String profilePicture, String password,
			List<Notification> notifications, String bio, String status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.profilePicture = profilePicture;
		this.password = password;
		this.notifications = notifications;
		this.isVerify = 0;
		this.otp = null;
		this.bio = bio;
		this.status = status;
	}
	
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public int isVerify() {
		return isVerify;
	}

	public void setVerify(int i) {
		this.isVerify = i;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", username=" + username+" email=" + email + ", profilePicture=" + profilePicture + 
		       ", password=" + password + ", isVerify=" + isVerify + ", otp=" + otp + ", bio=" + bio + 
		       ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", notifications=" + notifications + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, username, id, password, profilePicture, bio, status, createdAt, updatedAt);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && Objects.equals(username, other.username)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(profilePicture, other.profilePicture) && Objects.equals(bio, other.bio)
				&& Objects.equals(status, other.status) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(updatedAt, other.updatedAt);
	}

}
