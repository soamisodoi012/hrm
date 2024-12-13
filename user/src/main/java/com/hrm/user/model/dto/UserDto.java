package com.hrm.user.model.dto;

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
    private Float salary;
    private String title;
    private String sex;
    private Float age;
    private Float totalLeave;
    private String depId;
    private String role;
}