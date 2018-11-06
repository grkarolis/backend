package com.program.backend.controllers;

import com.program.backend.beans.entity.Team;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.request.AddTeamRequest;
import com.program.backend.beans.request.UpdateTeamRequest;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.team.TeamWithUsersResponse;
import com.program.backend.services.TeamService;
import com.program.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Component
@CrossOrigin(origins = "*")
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    TeamWithUsersResponse getTeamOverview(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
        return teamService.getTeamOverview(id, userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    TeamWithUsersResponse updateTeam(@PathVariable("id") Long id, @RequestBody UpdateTeamRequest request) {
        User user = userService.getUserById(request.getUserId());
        return teamService.updateSelectedTeam(user, id, request);
    }

    @RequestMapping(value = "/my-team", method = RequestMethod.GET)
    public @ResponseBody
    TeamWithUsersResponse getMyTeam(@RequestParam("userId") Long userId) {
        return teamService.getMyTeam(userId);
    }

    @RequestMapping(value = "/template/{teamId}", method = RequestMethod.GET)
    public @ResponseBody
    Set<SkillTemplateResponse> getTeamTemplate(@PathVariable("teamId") Long teamId) {
        Team team = teamService.getTeamById(teamId);
        return teamService.getTeamSkillTemplateResponseList(team);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TeamWithUsersResponse addTeam(@RequestBody AddTeamRequest request) {
        return teamService.addTeam(request);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<TeamWithUsersResponse> getAllTeams() {
        return teamService.getAllTeamOverviewResponses();
    }
}
