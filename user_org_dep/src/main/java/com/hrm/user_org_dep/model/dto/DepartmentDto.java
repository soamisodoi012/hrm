package com.hrm.user_org_dep.model.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
        private String depId;
        private String depName;
        private String organizationId; // Accept organizationId instead of Organization
        private String parentDepartmentId; // Accept parent department as an ID
        private List<DepartmentDto> subDepartments;
         @JsonProperty("parentDepartmentId")
    public String getParentDepartmentId() {
        return parentDepartmentId;
    }
    
    }

