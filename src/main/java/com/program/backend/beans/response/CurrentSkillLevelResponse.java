package com.program.backend.beans.response;

import com.program.backend.beans.entity.UserSkillLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurrentSkillLevelResponse extends Response {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String status;

    public CurrentSkillLevelResponse(UserSkillLevel userSkillLevel) {
        setId(userSkillLevel.getSkillLevel().getId());
        setTitle(userSkillLevel.getSkillLevel().getTitle());
        setStatus(userSkillLevel.getCurrentSkillLevelStatus());
    }
}
