// Update user request handler

package com.axis.team4.codecrafters.user_service.request;

public class UpdateUserRequest {
  private String fullName;
  private String profilePicture;
  private String bio;

  public UpdateUserRequest() {
  }

  public UpdateUserRequest(
      String fullName, String profilePicture, String bio) {
    this.fullName = fullName;
    this.profilePicture = profilePicture;
    this.bio = bio;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
}
