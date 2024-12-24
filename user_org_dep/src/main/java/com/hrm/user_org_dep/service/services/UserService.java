package com.hrm.user_org_dep.service.services;

import java.util.List;
import java.util.Optional;

import com.hrm.user_org_dep.model.dto.UserDto;
import com.hrm.user_org_dep.model.dto.UserResponse;
import com.hrm.user_org_dep.model.entity.User;

public interface UserService {
    UserResponse createUser(UserDto userDto);
    UserResponse getUserById(String username);
    List<UserResponse> getAllUser();
    UserResponse updateUser(String username,UserDto userDto);
    UserDto lockUser(String username);
}
