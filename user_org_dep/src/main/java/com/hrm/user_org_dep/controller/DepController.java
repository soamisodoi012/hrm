package com.hrm.user_org_dep.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.user_org_dep.model.dto.DepResponse;
import com.hrm.user_org_dep.model.dto.DepartmentDto;
import com.hrm.user_org_dep.service.services.DepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user/dep")
public class DepController {
    @Autowired
    private DepService depService;
    @PostMapping("/createDep")
    public DepResponse creaDepartment(@RequestBody DepartmentDto departmentDto){

return depService.createDepartment(departmentDto);
    }
}
