package com.hrm.user.service.services;

import com.hrm.user.model.dto.DepartmentDto;

import com.hrm.user.model.entity.Department;


public interface DepService {
    public Department createDepartment(DepartmentDto departmentDto);
}
