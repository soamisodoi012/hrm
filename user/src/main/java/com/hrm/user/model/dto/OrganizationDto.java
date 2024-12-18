package com.hrm.user.model.dto;

import java.util.List;

import com.hrm.user.model.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private String orgId;
    private String orgName;
    private String orgAddress;
    private String orgDescription;
}
