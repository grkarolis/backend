package com.program.backend.controllers;

import com.program.backend.beans.response.SkillEntityResponse;
import com.program.backend.beans.response.SkillHeaderResponse;
import com.program.backend.services.SkillHeaderService;
import com.program.backend.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/skill")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    SkillHeaderService skillHeaderService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SkillEntityResponse> getUser() {
        return skillService.getSkillEntityResponseList();
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public @ResponseBody
    List<SkillHeaderResponse> getSkillHeaders() {
        return skillHeaderService.getAllSkillHeaderTitles();
    }
}
