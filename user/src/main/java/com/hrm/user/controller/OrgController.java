package com.hrm.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.user.model.dto.OrganizationDto;
import com.hrm.user.model.entity.Organization;
import com.hrm.user.service.services.OrgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user/org")
public class OrgController {
    @Autowired
       private OrgService orgService;
       @PostMapping("/createOrg")
       public Organization postMethodName(@RequestBody OrganizationDto organizationDto) {
           //TODO: process POST request
           
           return orgService.createOrganization(organizationDto);
       }
       
    
}
