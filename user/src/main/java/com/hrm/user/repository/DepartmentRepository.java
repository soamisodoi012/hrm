package com.hrm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.user.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    
    
}
