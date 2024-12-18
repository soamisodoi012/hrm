package com.hrm.user.service.servicesImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.user.model.dto.DepartmentDto;
import com.hrm.user.model.entity.Department;
import com.hrm.user.model.entity.Organization;
import com.hrm.user.repository.DepartmentRepository;
import com.hrm.user.repository.OrganizationRepository;
import com.hrm.user.service.services.DepService;
@Service
public class DepServiceImp implements DepService {
    @Autowired
private DepartmentRepository departmentRepository;
@Autowired
private OrganizationRepository organizationRepository;
@Autowired
private ModelMapper modelMapper;    
@Override
public Department createDepartment(DepartmentDto departmentDto) {
    // Create and populate the Department entity
    Department department = new Department();
    department.setDepId(departmentDto.getDepId());
    department.setDepName(departmentDto.getDepName());

    // Fetch and set the Organization entity
    Organization organization = organizationRepository.findById(departmentDto.getOrganizationId())
            .orElseThrow(() -> new RuntimeException("Organization not found with ID: " + departmentDto.getOrganizationId()));
    department.setOrganization(organization);

    // Check for Parent Department: if null, set the department as its own parent
    if (departmentDto.getParentDepartmentId() != null) {
        Department parentDepartment = departmentRepository.findById(departmentDto.getParentDepartmentId())
                .orElseThrow(() -> new RuntimeException("Parent Department not found with ID: " + departmentDto.getParentDepartmentId()));
        department.setParentDepartment(parentDepartment);
    } else {
        // Save the department temporarily to set itself as the parent
        Department savedDepartment = departmentRepository.save(department);
        savedDepartment.setParentDepartment(savedDepartment); // Set itself as the parent
        return departmentRepository.save(savedDepartment);
    }

    return departmentRepository.save(department);
}

    
}
