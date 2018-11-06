package com.program.backend.controllers;

import com.program.backend.beans.response.SkillLevelResponse;
import com.program.backend.services.SkillLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/skill/levels")
public class SkillLevelController {

    @Autowired
    private SkillLevelService skillLevelService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<SkillLevelResponse> getSkillLevels() {
        return skillLevelService.getSkillLevelResponseList();
    }
}
