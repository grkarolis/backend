package com.program.backend.beans.response.user.skill;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.response.CurrentSkillLevelResponse;
import lombok.EqualsAndHashCode;

public class NonColleagueUserSkillResponse extends UserSkillResponse {

    public NonColleagueUserSkillResponse(Skill skill, UserSkillLevel userSkillLevel) {
        super(skill);
        this.setVotes(convertVoteEntityToVoteResponseList(userSkillLevel.getVotes()));
    }
}
