package com.hrm.user_org_dep.model.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
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
