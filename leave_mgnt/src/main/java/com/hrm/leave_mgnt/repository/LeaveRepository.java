package com.hrm.leave_mgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leave_mgnt.model.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave,String > {
    
}
