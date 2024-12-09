package com.hrm.leave_mgnt.services.ServiceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.leave_mgnt.model.entity.Leave;
import com.hrm.leave_mgnt.repository.LeaveRepository;
import com.hrm.leave_mgnt.services.LeaveService.LeaveService;
import com.hrm.leave_mgnt.services.LeaveService.UserFeignClient;
import com.hrm.user.model.entity.User;

@Service
public class LeaveServiceImp implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserFeignClient userFeignClient; // Feign client to call the user microservice

    @Override
    public Leave creatLeave(Leave leave) {
        // Call user-service to check if the user exists
        Optional<User> userOptional = userFeignClient.getUserById(leave.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Optionally, log or use the user's details
            return leaveRepository.save(leave);
        } else {
            // Handle user not found
            throw new RuntimeException("User with username " + leave.getUsername() + " does not exist.");
        }
    }
}
