package com.hrm.user_org_dep.model.dto;

import java.math.BigDecimal;

import com.hrm.user_org_dep.model.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username; // This acts as the primary key.
    private String address;
    private String phone;
    private String userId;
    private BigDecimal salary;
    private String title;
    private String sex;
    private BigDecimal age;
    private BigDecimal totalLeave;
    private String department;
    private String role;
    private String experiance;
    private boolean isLocked;
    
}
