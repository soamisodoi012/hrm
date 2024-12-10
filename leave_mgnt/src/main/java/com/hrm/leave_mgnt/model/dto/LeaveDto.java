package com.hrm.leave_mgnt.model.dto;

import com.hrm.leave_mgnt.constants.leave_type;
public class LeaveDto {
    private String leaveId;
    private String username;
    private leave_type leave_type;
    public LeaveDto(){

    }
    public LeaveDto(String leaveId,String username,leave_type leave_type){
        this.leaveId=leaveId;
        this.username=username;
        this.leave_type=leave_type;

    }
    public void setLeaveType(leave_type leaveType) {
        this.leave_type = leaveType;
    }
    public leave_type getLeave_type() {
        return leave_type;
    }
    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }
    public String getLeaveId() {
        return leaveId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
