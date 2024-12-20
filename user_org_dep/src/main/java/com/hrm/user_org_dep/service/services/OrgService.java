package com.hrm.user_org_dep.service.services;

import com.hrm.user_org_dep.model.dto.OrganizationDto;
import com.hrm.user_org_dep.model.entity.Organization;


public interface OrgService {
     public Organization createOrganization(OrganizationDto organizationDto);
    
}
