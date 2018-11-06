package com.program.backend.beans.response.user.skill;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.response.CurrentSkillLevelResponse;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class ColleagueUserSkillResponse extends UserSkillResponse {

    public ColleagueUserSkillResponse(Skill skill, UserSkillLevel userSkillLevel) {
        super(skill);
        this.setLevel(new CurrentSkillLevelResponse(userSkillLevel));
        this.setVotes(convertVoteEntityToVoteResponseList(userSkillLevel.getVotes()));
    }
}
