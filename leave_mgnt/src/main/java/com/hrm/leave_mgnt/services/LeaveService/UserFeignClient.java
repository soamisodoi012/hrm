package com.hrm.leave_mgnt.services.LeaveService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrm.leave_mgnt.model.dto.UserDto;
//import com.hrm.user.model.entity.User;

import java.util.Optional;

@FeignClient(name = "user", path = "/user") // Match the base path of the `user` service
public interface UserFeignClient {

    @GetMapping("/{username}")
    Optional<UserDto> getUserById(@PathVariable("username") String username);
}
