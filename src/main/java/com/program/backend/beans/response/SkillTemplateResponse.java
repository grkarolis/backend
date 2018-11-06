package com.program.backend.beans.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(of = "skill")
public class SkillTemplateResponse implements Comparable<SkillTemplateResponse>{

    @NonNull
    private SkillEntityResponse skill;

    @NonNull
    private Integer userCounter;

    @NonNull
    private Double averageLevel;

    @Override
    public int compareTo(SkillTemplateResponse o) {
        if (skill.equals(o.skill))
        return 0;

        int counterSort = o.getUserCounter().compareTo(this.userCounter);
        return (counterSort != 0) ? counterSort : 1;
    }
}
