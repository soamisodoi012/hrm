package com.hrm.user_org_dep.service.services;

import java.util.List;

import com.hrm.user_org_dep.model.dto.DepResponse;
import com.hrm.user_org_dep.model.dto.DepartmentDto;
public interface DepService {
    public DepResponse createDepartment(DepartmentDto departmentDto);
    DepResponse getDepById(String depId);
    List<DepResponse> getAllDep();
    DepResponse updateDep(String depId,DepartmentDto departmentDto);
}
