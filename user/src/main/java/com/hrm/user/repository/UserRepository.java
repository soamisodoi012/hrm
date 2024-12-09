package com.hrm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.user.model.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
    
}
