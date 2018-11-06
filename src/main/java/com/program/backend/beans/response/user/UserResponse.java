package com.program.backend.beans.response.user;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.enums.Role;
import com.program.backend.beans.response.Response;
import com.program.backend.beans.response.team.TeamResponse;
import com.program.backend.beans.response.user.skill.UserSkillResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends Response {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private List<? extends UserSkillResponse> skills;

    private TeamResponse team;

    private Role role;

    public UserResponse(User user) {
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        setTeam(user.getTeam().map(TeamResponse::new).orElse(null));
        setRole(user.getRole());
    }
}
