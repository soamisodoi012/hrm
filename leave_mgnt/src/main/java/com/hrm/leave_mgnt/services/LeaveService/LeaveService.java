package com.hrm.leave_mgnt.services.LeaveService;

import java.util.Optional;

import com.hrm.leave_mgnt.model.dto.LeaveDto;
import com.hrm.leave_mgnt.model.entity.Leave;

public interface LeaveService {
    Leave createLeave(LeaveDto leaveDto);
   // Optional<Leave> findLeaveByUsername(String username); // Change to String
    LeaveDto updateLeave(String leaveId,LeaveDto leave); // Change to Leave
}
