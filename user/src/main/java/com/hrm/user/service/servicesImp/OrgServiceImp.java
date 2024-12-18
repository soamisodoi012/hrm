package com.hrm.user.service.servicesImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.user.model.dto.OrganizationDto;
import com.hrm.user.model.entity.Organization;
import com.hrm.user.repository.OrganizationRepository;
import com.hrm.user.service.services.OrgService;
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
 