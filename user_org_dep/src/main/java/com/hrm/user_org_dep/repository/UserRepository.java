package com.hrm.user_org_dep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.user_org_dep.model.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
    
}
