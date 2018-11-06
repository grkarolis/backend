package com.program.backend.beans.response.team;

import com.program.backend.beans.entity.Team;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.user.UserResponse;

import java.util.List;
import java.util.Set;

public class ColleagueTeamOverviewWithUsersResponse extends TeamWithUsersResponse {

    public ColleagueTeamOverviewWithUsersResponse(Team team, List<UserResponse> usersWithSkills, Set<SkillTemplateResponse> skillTemplateResponses) {
        super(team, usersWithSkills, skillTemplateResponses);
    }

    public ColleagueTeamOverviewWithUsersResponse(Team team, List<UserResponse> usersWithSkills) {
        super(team, usersWithSkills);
    }
}
