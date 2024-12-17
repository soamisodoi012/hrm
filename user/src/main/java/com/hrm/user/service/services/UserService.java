package com.hrm.user.service.services;

import java.util.List;
import java.util.Optional;

import com.hrm.user.model.dto.UserDto;
import com.hrm.user.model.entity.User;

public interface UserService {
    User createUser(UserDto userDto);
    Optional<User> getUserById(String username);
    List<User> getAllUser();
    UserDto updateUser(String username,UserDto userDto);
    UserDto lockUser(String username);
}
