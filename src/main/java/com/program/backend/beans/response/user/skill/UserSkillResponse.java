package com.program.backend.beans.response.user.skill;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.Vote;
import com.program.backend.beans.response.CurrentSkillLevelResponse;
import com.program.backend.beans.response.VoteResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSkillResponse {

    private Long id;

    private String title;

    private String header;

    private List<VoteResponse> votes;

    private CurrentSkillLevelResponse level;

    public UserSkillResponse(Skill skill) {
        setId(skill.getId());
        setTitle(skill.getTitle());
        setHeader(skill.getSkillHeader().getTitle());
    }

    public List<VoteResponse> convertVoteEntityToVoteResponseList(List<Vote> votes) {
        return votes.stream().map(VoteResponse::new).collect(Collectors.toList());
    }
}
