package com.program.backend.beans.response.team;

import com.program.backend.beans.entity.Team;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
public class TeamWithUsersResponse extends TeamResponse {

    protected List<UserResponse> users;

    public TeamWithUsersResponse(Team team, List<UserResponse> usersWithSkills, Set<SkillTemplateResponse> skillTemplateResponses) {
        super(team, skillTemplateResponses);
        this.users = usersWithSkills;
    }

    public TeamWithUsersResponse(Team team, List<UserResponse> usersWithSkills) {
        super(team, new HashSet<>());
        this.users = usersWithSkills;
    }

    public List<UserResponse> getUsers() {
        return users;
    }
}
