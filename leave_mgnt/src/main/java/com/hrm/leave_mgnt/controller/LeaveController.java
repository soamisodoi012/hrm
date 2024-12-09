package com.hrm.leave_mgnt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hrm.leave_mgnt.model.entity.Leave;

import com.hrm.leave_mgnt.services.LeaveService.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/leave_mgnt")
@CrossOrigin(origins = "http://localhost:8082")
public class LeaveController {
  @Autowired
    private LeaveService leaveService;
    @PostMapping("/createLeave")
    public Leave creatLeave(@PathVariable String username,@RequestBody Leave leave) {
        return leaveService.creatLeave(leave);
    }
    
    
}
