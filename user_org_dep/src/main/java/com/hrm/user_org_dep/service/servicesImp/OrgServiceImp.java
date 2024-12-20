package com.hrm.user_org_dep.service.servicesImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.user_org_dep.model.dto.OrganizationDto;
import com.hrm.user_org_dep.model.entity.Organization;
import com.hrm.user_org_dep.repository.OrganizationRepository;
import com.hrm.user_org_dep.service.services.OrgService;
@Service
public class OrgServiceImp implements OrgService {
@Autowired
private OrganizationRepository organizationRepository;
@Autowired
private ModelMapper modelMapper;
public OrgServiceImp(OrganizationRepository organizationRepository){
    this.organizationRepository=organizationRepository;
}
    @Override
    public Organization createOrganization(OrganizationDto organizationDto) {
       
        Organization organization = modelMapper.map(organizationDto, Organization.class);
        return organizationRepository.save(organization);
    }
    
}
 