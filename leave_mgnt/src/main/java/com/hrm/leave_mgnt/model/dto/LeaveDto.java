package com.hrm.leave_mgnt.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class LeaveDto {
    private String leaveId;
    private String username;
    private String leave_type;
    
}
