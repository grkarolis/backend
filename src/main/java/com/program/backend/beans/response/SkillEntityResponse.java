package com.program.backend.beans.response;

import com.program.backend.beans.entity.Skill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SkillEntityResponse extends Response {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String header;

    public SkillEntityResponse(@NonNull Skill skill) {
        setId(skill.getId());
        setTitle(skill.getTitle());
        setHeader(skill.getSkillHeader().getTitle());
    }
}
