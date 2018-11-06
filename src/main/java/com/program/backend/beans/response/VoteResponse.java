package com.program.backend.beans.response;

import com.program.backend.beans.entity.Vote;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VoteResponse extends Response {

    private Long voter;

    private Long skillId;

    private String message;

    public VoteResponse(Vote vote) {
        setVoter(vote.getVoter().getId());
        setSkillId(vote.getUserSkillLevel().getUserSkill().getSkill().getId());
        setMessage(vote.getMessage());
    }
}
