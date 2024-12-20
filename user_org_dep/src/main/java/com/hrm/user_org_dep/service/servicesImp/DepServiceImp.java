package com.hrm.user_org_dep.service.servicesImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.user_org_dep.model.dto.DepResponse;
import com.hrm.user_org_dep.model.dto.DepartmentDto;
import com.hrm.user_org_dep.model.entity.Department;
import com.hrm.user_org_dep.model.entity.Organization;
import com.hrm.user_org_dep.repository.DepartmentRepository;
import com.hrm.user_org_dep.repository.OrganizationRepository;
import com.hrm.user_org_dep.service.services.DepService;
@Service
public class DepServiceImp implements DepService {
    @Autowired
private DepartmentRepository departmentRepository;
@Autowired
private OrganizationRepository organizationRepository;
@Autowired
private ModelMapper modelMapper;    
@Override
public DepResponse createDepartment(DepartmentDto departmentDto) {
    // Create a new Department entity
    Department department = new Department();
    department.setDepId(departmentDto.getDepId());
    department.setDepName(departmentDto.getDepName());
    
    // Fetch and assign parent department if it exists, else set null
    Department parentDepartment = departmentDto.getParentDepartmentId() != null
            ? departmentRepository.findById(departmentDto.getParentDepartmentId()).orElse(null)
            : null;
    department.setParentDepartment(parentDepartment);
    
    // Fetch and assign the organization entity
    Organization organization = organizationRepository.findById(departmentDto.getOrganizationId())
            .orElseThrow(() -> new RuntimeException("Organization not found with ID: " + departmentDto.getOrganizationId()));
    department.setOrganization(organization);

    // Save the department entity
    departmentRepository.save(department);
    
    // Create and return DepResponse DTO
    DepResponse depResponse = new DepResponse(
            department.getDepId(),
            department.getDepName(),
            organization.getOrgId(),
            (parentDepartment != null) ? parentDepartment.getDepId() : null
    );

    return depResponse;
}
   
}
