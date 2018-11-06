package com.program.backend.events;

import com.program.backend.beans.entity.UserSkill;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserSkillAddedEvent {

    @NonNull
    private UserSkill userSkill;
}
