package com.hrm.leave_mgnt.controller;

import com.hrm.leave_mgnt.model.dto.LeaveDto;
import com.hrm.leave_mgnt.model.entity.Leave;
import com.hrm.leave_mgnt.services.LeaveService.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveDto> approve(@PathVariable String leaveId) {
        // Call service to approve leave
        LeaveDto updatedLeave = leaveService.updateLeave(leaveId, null);
        System.out.println("Updated Leave: " + updatedLeave);
        return ResponseEntity.ok(updatedLeave);
    }
    
}