package com.program.backend.beans.response;

import com.program.backend.beans.entity.SkillLevel;
import lombok.Data;

@Data
public class SkillLevelResponse {

    private Long id;

    private Long level;

    private String title;

    private String description;

    public SkillLevelResponse(SkillLevel skillLevel) {
        setId(skillLevel.getId());
        setLevel(skillLevel.getLevel());
        setTitle(skillLevel.getTitle());
        setDescription(skillLevel.getDescription());
    }
}
