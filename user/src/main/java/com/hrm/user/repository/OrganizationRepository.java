package com.hrm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.user.model.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
