package com.program.backend.services;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.entity.Vote;
import com.program.backend.beans.request.VoteUserSkillRequest;
import com.program.backend.beans.response.VoteResponse;
import com.program.backend.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSkillLevelService userSkillLevelService;

    public VoteResponse voteUserSkill(VoteUserSkillRequest request) {
        User voter = userService.getUserById(request.getVoterId());

        if (request.getVoterId().equals(request.getUserId()))
            throw new RuntimeException("can't vote for yourself");

        UserSkillLevel userSkillLevel = userSkillLevelService
                .getCurrentUserSkillLevelByUserIdAndSkillId(request.getUserId(), request.getSkillId());

        Vote vote = new Vote(voter, userSkillLevel, request.getMessage());
        Vote savedVote = voteRepository.save(vote);

        return new VoteResponse(savedVote);
    }
}
