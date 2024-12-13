package com.hrm.leave_mgnt.model.dto;

import java.util.Date;

import com.hrm.leave_mgnt.constants.leave_type;
public class LeaveDto {
    private String leaveId;
    private String username;
    private leave_type leave_type;
     private String status;
    private Date startDate;
    private Date  endDate;
    public LeaveDto(){

    }
    public LeaveDto(String leaveId,String username,leave_type leave_type, String status, Date startDate, Date  endDate){
        this.leaveId=leaveId;
        this.username=username;
        this.leave_type=leave_type;
        this.endDate=endDate;
        this.startDate=startDate;
        this.status=status;

    }
    public Date getEndDate() {
        return endDate;
    } public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
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
