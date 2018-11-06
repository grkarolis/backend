package com.program.backend.beans.response.user;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.response.user.skill.UserSkillResponse;

import java.util.List;

public class UserWithSkillResponse extends UserResponse {

    public UserWithSkillResponse(User user, List<UserSkillResponse> skills) {
        super(user);
        this.setSkills(skills);
    }
}
