package com.program.backend.events;

import com.program.backend.beans.entity.UserSkill;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserSkillRemovedEvent {

    @NonNull
    private UserSkill userSkill;
}
