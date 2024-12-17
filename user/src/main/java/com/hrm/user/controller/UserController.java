package com.hrm.user.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrm.user.model.dto.UserDto;
import com.hrm.user.model.entity.User;
import com.hrm.user.service.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // Constructor injection (this is preferred for better testability)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // API endpoint for creating a user
    @PostMapping("/createUser")
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable("username")String username){
      return userService.getUserById(username);
      
    }@PostMapping("/updateUser/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }
    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @PostMapping("/lockUser/{username}")
    public boolean lockUser(@PathVariable String username) {
        
        userService.lockUser(username);
        return true;
    }
      
}
