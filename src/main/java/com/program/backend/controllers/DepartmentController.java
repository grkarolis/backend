package com.program.backend.controllers;

import com.program.backend.beans.response.department.DepartmentEntityResponse;
import com.program.backend.beans.response.department.DepartmentOverviewResponse;
import com.program.backend.services.DepartmentService;
import com.program.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@CrossOrigin(origins = "*")
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DepartmentEntityResponse> getAll() {
        return departmentService.getAllDepartmentEntityResponseList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    DepartmentOverviewResponse getDepartmentOverviewById(@RequestParam("userId") Long userId,
                                                         @PathVariable("id") Long id) {
        return departmentService.getDepartmentOverviewByDepartmentIdAndUser(id, userService.getUserById(userId));
    }
}
