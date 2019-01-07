package com.program.backend.beans.response;

import com.program.backend.beans.entity.SkillHeader;
import lombok.Data;

@Data
public class SkillHeaderResponse extends Response {

    private Long id;

    private String title;

    public SkillHeaderResponse(SkillHeader skillHeader) {
        setId(skillHeader.getId());
        setTitle(skillHeader.getTitle());
    }

}
