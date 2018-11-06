package com.program.backend.events;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.entity.UserSkill;
import com.program.backend.beans.entity.Vote;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserVotedForUserSkillEvent {

    @NonNull
    private User voter;

    @NonNull
    private User receiver;

    @NonNull
    private Vote vote;
}
