package com.hrm.user_org_dep.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepResponse {
     private String depId;
    private String depName;
    private String organizationId; // Accept organizationId instead of Organization
    private String parentDepartmentId; // Accept parent department as an ID
    
}
