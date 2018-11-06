package com.program.backend.controllers;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.entity.UserSkill;
import com.program.backend.beans.request.AddSkillRequest;
import com.program.backend.beans.request.AssignSkillLevelRequest;
import com.program.backend.beans.request.VoteUserSkillRequest;
import com.program.backend.beans.response.VoteResponse;
import com.program.backend.beans.response.user.skill.ColleagueUserSkillResponse;
import com.program.backend.beans.response.user.skill.UserSkillResponse;
import com.program.backend.services.ApprovalService;
import com.program.backend.services.UserSkillService;
import com.program.backend.services.VoteService;
import com.program.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "user/skill")
public class UserSkillController {

    private final UserService userService;
    private final UserSkillService userSkillService;
    private final VoteService voteService;
    private final ApprovalService approvalService;

    @Autowired
    public UserSkillController(UserService userService, UserSkillService userSkillService,
                               ApprovalService approvalService, VoteService voteService) {
        this.userService = userService;
        this.userSkillService = userSkillService;
        this.approvalService = approvalService;
        this.voteService = voteService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ColleagueUserSkillResponse addUserSkill(@Valid @RequestBody AddSkillRequest addSkillRequest) {
        return userSkillService.addUserSkill(addSkillRequest.getUserId(), addSkillRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    UserSkillResponse removeUserSkill(@PathVariable("id") Long id, @RequestParam("skillId") Long skillId) {
        return userSkillService.removeUserSkill(id, skillId);
    }

    @RequestMapping(value = "level", method = RequestMethod.POST)
    public @ResponseBody
    UserSkillResponse assignUserSkillLevel(@Valid @RequestBody AssignSkillLevelRequest request) {
        ApprovalRequest approvalRequest = approvalService
                .addSkillLevelApprovalRequestWithNotifications(request.getUserId(), request);
        UserSkill userSkill = approvalRequest.getUserSkillLevel().getUserSkill();

        return new ColleagueUserSkillResponse(userSkill.getSkill(), userSkillService.getCurrentSkillLevelStatus(userSkill));
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public @ResponseBody
    VoteResponse voteUserSkillLevel(@Valid @RequestBody VoteUserSkillRequest request) {
        return voteService.voteUserSkill(request);
    }



}
