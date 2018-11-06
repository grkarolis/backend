package com.program.backend.events;

import com.program.backend.beans.entity.UserSkillLevel;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserSkillLevelRaisedEvent {

    @NonNull
    private UserSkillLevel userSkillLevel;
}
