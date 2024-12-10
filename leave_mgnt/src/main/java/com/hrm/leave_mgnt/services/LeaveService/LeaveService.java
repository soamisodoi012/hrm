package com.hrm.leave_mgnt.services.LeaveService;

import com.hrm.leave_mgnt.model.dto.LeaveDto;
import com.hrm.leave_mgnt.model.entity.Leave;

public interface LeaveService {
    public Leave creatLeave(LeaveDto leaveDto);
    
}
