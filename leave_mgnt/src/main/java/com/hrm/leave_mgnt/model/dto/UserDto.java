package com.hrm.leave_mgnt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username; // This acts as the primary key.
    private String address;
    private String phone;
    private String userId;
    private Float salary;
    private String title;
    private String sex;
    private Float age;
    private Double totalLeave;
    private String depId;
    private String role;
    private String experiance;
    private boolean isLocked;
}
