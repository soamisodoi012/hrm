package com.hrm.user.service.services;

import com.hrm.user.model.dto.OrganizationDto;
import com.hrm.user.model.entity.Organization;


public interface OrgService {
     public Organization createOrganization(OrganizationDto organizationDto);
    
}
