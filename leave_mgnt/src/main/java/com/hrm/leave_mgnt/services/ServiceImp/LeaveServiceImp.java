package com.hrm.leave_mgnt.services.ServiceImp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    @Override
public Leave approveLeave(String leaveId) {
    // Find the leave entity by ID
    Leave existingLeave = leaveRepository.findById(leaveId)
            .orElseThrow(() -> new ResourceNotFoundException("Leave not found with ID: " + leaveId));

    // Update the status to approved
    existingLeave.setStatus("approved");

    // Save the updated entity
    LocalDateTime startDateTime = existingLeave.getStartDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime endDateTime = existingLeave.getEndDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
    long numberOfDays = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate()) + 1;
    System.out.println("days"+numberOfDays);
    leaveRepository.save(existingLeave);
    userFeignClient.lockUser(existingLeave.getUsername());
    UserDto userDto=new UserDto();
    Optional<UserDto> userOptional = userFeignClient.getUserById(existingLeave.getUsername());
    System.out.println("userOptional.get().getTotalLeave()"+ userOptional.get().getTotalLeave()+ userOptional);
    userDto.setTotalLeave((Double) (userOptional.get().getTotalLeave() - numberOfDays));
    userFeignClient.updateUser(existingLeave.getUsername(),userDto);
    // Return the updated DTO
    return existingLeave;//modelMapper.map(existingLeave, LeaveDto.class);
}

    // @Override
    // public Leave createLeave(LeaveDto leaveDto) {

    //         // Call user-service to check if the user exists
    //         Optional<UserDto> userOptional = userFeignClient.getUserById(leaveDto.getUsername());
    //         System.out.println(userOptional+"userOptional");
    //         if (userOptional.isPresent()) {
    //             leaveDto.setStatus("new");
    //             leaveDto.setEndDate(new Date());
    //             leaveDto.setStartDate(new Date());

    //             Leave leave = modelMapper.map(leaveDto, Leave.class);
    //             // Save the Leave entitycd desktoop
    //             return leaveRepository.save(leave);
    //         } else {
    //             // Handle user not found
    //             throw new ResourceNotFoundException("User with username " + leaveDto.getUsername() + " does not exist.");
    //         }
        
    //     }
    @Override
    public Leave createLeave(LeaveDto leaveDto) {
    
        // Call user-service to check if the user exists
        Optional<UserDto> userOptional = userFeignClient.getUserById(leaveDto.getUsername());
        System.out.println(userOptional + " userOptional");
    
        if (userOptional.isPresent()) {
            // Get the current time as LocalDateTime and truncate it to only year, month, day, hour, minute
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    
            // Convert LocalDateTime to Date
            Date truncatedStartDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
           // Date truncatedEndDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    
            // Set truncated dates
            leaveDto.setStartDate(truncatedStartDate);
            // leaveDto.setEndDate(truncatedEndDate);
    
            leaveDto.setStatus("new");
    
            // Map the LeaveDto to Leave entity
            Leave leave = modelMapper.map(leaveDto, Leave.class);
    
            // Save the Leave entity
            return leaveRepository.save(leave);
        } else {
            // Handle user not found
            throw new ResourceNotFoundException("User with username " + leaveDto.getUsername() + " does not exist.");
        }
    }
    @Override
    public Optional<Leave> viewLeave(String leaveId) {
        
      return leaveRepository.findById(leaveId);
    }
    }


