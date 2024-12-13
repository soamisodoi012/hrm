package com.hrm.leave_mgnt.services.ServiceImp;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.leave_mgnt.exception.ResourceNotFoundException;
import com.hrm.leave_mgnt.model.dto.LeaveDto;
import com.hrm.leave_mgnt.model.dto.UserDto;
import com.hrm.leave_mgnt.model.entity.Leave;
import com.hrm.leave_mgnt.repository.LeaveRepository;
import com.hrm.leave_mgnt.services.LeaveService.LeaveService;
import com.hrm.leave_mgnt.services.LeaveService.UserFeignClient;

@Service
public class LeaveServiceImp implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserFeignClient userFeignClient; // Feign client to call the user microservice

    @Autowired
    private ModelMapper modelMapper; // ModelMapper for automatic mapping
    // @Override
   
    // public Optional<Leave> findLeaveByUsername(String username) {
    //     // Find leave by username
    //     return leaveRepository.findByUsername(username);
    // }
    @Override
public LeaveDto updateLeave(String leaveId, LeaveDto leaveDto) {
    // Find the leave entity by ID
    Leave existingLeave = leaveRepository.findById(leaveId)
            .orElseThrow(() -> new ResourceNotFoundException("Leave not found with ID: " + leaveId));

    // Update the status to approved
    existingLeave.setStatus("approved");

    // Save the updated entity
    leaveRepository.save(existingLeave);
    System.out.println("Updated Leave Entity: " + existingLeave);

    // Return the updated DTO
    return modelMapper.map(existingLeave, LeaveDto.class);
}

    @Override
    public Leave createLeave(LeaveDto leaveDto) {

            // Call user-service to check if the user exists
            Optional<UserDto> userOptional = userFeignClient.getUserById(leaveDto.getUsername());
    
            if (userOptional.isPresent()) {
                leaveDto.setStatus("new");
                leaveDto.setEndDate(new Date());
                leaveDto.setStartDate(new Date());

                Leave leave = modelMapper.map(leaveDto, Leave.class);
    
                // Save the Leave entitycd desktoop
                return leaveRepository.save(leave);
            } else {
                // Handle user not found
                throw new ResourceNotFoundException("User with username " + leaveDto.getUsername() + " does not exist.");
            }
        }
    }


