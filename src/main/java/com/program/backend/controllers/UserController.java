package com.program.backend.controllers;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.request.AssignTeamRequest;
import com.program.backend.beans.request.SearchColleagueRequest;
import com.program.backend.beans.response.user.UserResponse;
import com.program.backend.beans.response.user.UserWithSkillResponse;
import com.program.backend.services.SearchService;
import com.program.backend.services.UserService;
import com.program.backend.services.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Component
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserSkillService userSkillService;

    @Autowired
    private SearchService searchService;

    @Autowired
    public UserController(UserService userService, UserSkillService userSkillService) {
        this.userService = userService;
        this.userSkillService = userSkillService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserResponse getUserProfile(@PathVariable("id") String id) {
        return userService.getMyProfile(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<UserResponse> getAllUsers() {
        return userService.getAllUserResponses();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<UserResponse> searchColleagues(Long userId, String query) {
        return searchService.searchColleagues(userId, query);
    }

    @RequestMapping(value = "/team", method = RequestMethod.PUT)
    public @ResponseBody
    UserWithSkillResponse assignUserTeam(@RequestBody AssignTeamRequest request) {
        User user = userService.getUserById(request.getLoggedUserId());
        userService.assignTeam(user.getId(), request);
        return new UserWithSkillResponse(userService.assignTeam(user.getId(), request),
                userSkillService.getNormalUserSkillResponseList(user.getActiveUserSkills()));
    }
}
