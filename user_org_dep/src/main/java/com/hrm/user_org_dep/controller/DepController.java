package com.hrm.user_org_dep.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.user_org_dep.model.dto.DepResponse;
import com.hrm.user_org_dep.model.dto.DepartmentDto;
import com.hrm.user_org_dep.service.services.DepService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/user/dep")
public class DepController {
    @Autowired
    private DepService depService;
    @PostMapping("/createDep")
    public DepResponse creaDepartment(@RequestBody DepartmentDto departmentDto){

return depService.createDepartment(departmentDto);
    }
    @GetMapping("/{depId}")
    public DepResponse getDepById(@PathVariable String depId) {
        return depService.getDepById(depId);
    }
    @GetMapping("/getAllDep")
    public List<DepResponse> getAllDep() {
        return depService.getAllDep();
    }
    @GetMapping("/update/{depId}")
    public DepResponse updateDep(@PathVariable String depId ,@RequestBody DepartmentDto departmentDto) {
        return depService.updateDep(depId, departmentDto);
    }
    
}
