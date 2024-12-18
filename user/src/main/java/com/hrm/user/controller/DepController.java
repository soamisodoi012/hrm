package com.hrm.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.user.model.dto.DepartmentDto;
import com.hrm.user.model.entity.Department;
import com.hrm.user.service.services.DepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user/dep")
public class DepController {
    @Autowired
    private DepService depService;
    @PostMapping("/createDep")
    public Department creaDepartment(@RequestBody DepartmentDto departmentDto){

return depService.createDepartment(departmentDto);
    }
}
