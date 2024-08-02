// User Controller class for handling user requests

package com.axis.team4.codecrafters.user_service.controller;

import com.axis.team4.codecrafters.user_service.controller.mapper.UserDtoMapper;
import com.axis.team4.codecrafters.user_service.dto.UserDto;
import com.axis.team4.codecrafters.user_service.exception.UserException;
import com.axis.team4.codecrafters.user_service.modal.User;
import com.axis.team4.codecrafters.user_service.request.UpdateUserRequest;
import com.axis.team4.codecrafters.user_service.service.UserService;
import java.util.HashSet;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }
  
 // Update user request handler 

  @PutMapping("/update/{userId}")
  public ResponseEntity<UserDto> updateUserHandler(
      @RequestBody UpdateUserRequest req, @PathVariable Integer userId)
      throws UserException {
    User updatedUser = userService.updateUser(userId, req);
    UserDto userDto = UserDtoMapper.toUserDTO(updatedUser);

    return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
  }

// Profile request handler  
  
  @GetMapping("/profile")
  public ResponseEntity<UserDto> getUserProfileHandler(
      @RequestHeader("Authorization") String jwt) throws UserException {

    User user = userService.findUserProfile(jwt);

    UserDto userDto = UserDtoMapper.toUserDTO(user);


    return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
  }
  
// Search user request handler  

  @GetMapping("/search")
  public ResponseEntity<HashSet<UserDto>> searchUsersByName(
      @RequestParam("name") String name) {

    List<User> users = userService.searchUser(name);

    HashSet<User> set = new HashSet<>(users);

    HashSet<UserDto> userDtos = UserDtoMapper.toUserDtos(set);


    return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
  }
}
