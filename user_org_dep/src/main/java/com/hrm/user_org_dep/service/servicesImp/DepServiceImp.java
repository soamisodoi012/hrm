package com.hrm.user_org_dep.service.servicesImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public DepResponse createDepartment(DepartmentDto departmentDto) {
        // Create a new Department entity
        Department department = new Department();
        department.setDepId(departmentDto.getDepId());
        department.setDepName(departmentDto.getDepName());
    DepResponse  depResponse=new DepResponse();
        Optional<Organization> org = organizationRepository.findById(departmentDto.getOrganizationId());
        if (org.isPresent()) {
            Organization organization=org.get();
            department.setOrganization(organization); 
            depResponse.setOrganizationId(organization.getOrgId()); // Set the Organization entity
        }
    
        if (departmentDto.getParentDepartmentId() != null) {
            Optional<Department> parentDept = departmentRepository.findById(departmentDto.getParentDepartmentId());
            if (parentDept.isPresent()) {
                Department dep=parentDept.get();
                department.setParentDepartment(dep);
                depResponse.setParentDepartmentId(dep.getDepId());// Set the parent Department entity
            }
        }
    
        departmentRepository.save(department);
        
        // Create and return DepResponse DTO
               depResponse.setDepId(department.getDepId());
               depResponse.setDepName(department.getDepName());
                 // Assuming Org has getOrgId()    
        return depResponse;
    }
@Override
    public DepResponse getDepById(String depId) {

          Optional<Department> department=  departmentRepository.findById(depId);
            DepResponse depResponse=new DepResponse();
            
            if (department.isPresent()) {
                depResponse.setDepId(department.get().getDepId());
                depResponse.setDepName(department.get().getDepName());
              depResponse.setParentDepartmentId(department.get().getParentDepartment().getDepId());
              depResponse.setOrganizationId(department.get().getOrganization().getOrgId());
            }
            return depResponse;
    }
@Override
 public List<DepResponse> getAllDep() {
        List<Department> departments = departmentRepository.findAll(); // Fetch all departments
        return departments.stream()
                          .map(this::convertToDepResponse) // Convert each Department to DepResponse
                          .collect(Collectors.toList());  // Use Collectors.toList() for Java 8 compatibility
    }

    // Conversion method: Department to DepResponse
    private DepResponse convertToDepResponse(Department department) {
        DepResponse response = new DepResponse();
        response.setDepId(department.getDepId());
        response.setDepName(department.getDepName());
        response.setOrganizationId(department.getOrganization() != null ? department.getOrganization().getOrgId() : null);
        response.setParentDepartmentId(department.getParentDepartment() != null ? department.getParentDepartment().getDepId() : null);

        return response;
    }

//
@Override
public DepResponse updateDep(String depId, DepartmentDto departmentDto) {
    Optional<Department> optionalDepartment=departmentRepository.findById(depId);
    Optional<Organization> optionalOrganization;
    Optional<Department> optionalDepartmentParent;
    DepResponse depResponse= new DepResponse();
    if (optionalDepartment.isPresent()) {
        Department department=optionalDepartment.get();
        if (departmentDto.getDepName()!=null)
        {
         department.setDepName(departmentDto.getDepName());
         depResponse.setDepName(department.getDepName());
        }
        if (departmentDto.getParentDepartmentId()!=null){
            optionalDepartmentParent =departmentRepository.findById(departmentDto.getParentDepartmentId());
        department.setParentDepartment(optionalDepartmentParent.get());
        depResponse.setParentDepartmentId(department.getParentDepartment().getDepId());
    }
        if (departmentDto.getOrganizationId()!=null) {
            optionalOrganization=organizationRepository.findById(departmentDto.getOrganizationId());
         department.setOrganization(optionalOrganization.get()); 
         depResponse.setOrganizationId(department.getOrganization().getOrgId());
         }
        departmentRepository.save(department);

       depResponse.setDepId(depId);
       depResponse.setOrganizationId(department.getOrganization().getOrgId());
       depResponse.setParentDepartmentId(department.getParentDepartment().getDepId());
       return depResponse;
       }
       else return null;
      
}
}
