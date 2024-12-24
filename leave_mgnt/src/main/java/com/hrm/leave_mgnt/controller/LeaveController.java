package com.hrm.leave_mgnt.controller;

import com.hrm.leave_mgnt.model.dto.LeaveDto;
import com.hrm.leave_mgnt.model.entity.Leave;
import com.hrm.leave_mgnt.services.LeaveService.LeaveService;

import jakarta.ws.rs.GET;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = "http://localhost:8082")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // Endpoint to create a leave
    @PostMapping("/createLeave")
    public ResponseEntity<Leave> createLeave(@RequestBody LeaveDto leaveDto) {
        Leave createdLeave = leaveService.createLeave(leaveDto);
        return ResponseEntity.ok(createdLeave);
    }
    @GetMapping("/approve/{leaveId}")
    public Leave approve(@PathVariable String leaveId) {
        // Call service to approve leave
        Leave updatedLeave = leaveService.approveLeave(leaveId);
        return updatedLeave;
    }
    @GetMapping("/{leaveId}")
    public Optional<Leave> getLeave(@PathVariable String leaveId) {
        return leaveService.viewLeave(leaveId);
    }
    
    
}