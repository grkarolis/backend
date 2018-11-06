package com.program.backend.beans.response.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.program.backend.beans.entity.Team;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.department.DepartmentResponse;
import com.program.backend.beans.response.division.DivisionResponse;
import com.program.backend.beans.response.valuestream.ValueStreamResponse;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {

    protected Long id;

    protected String name;

    protected DepartmentResponse department;

    protected DivisionResponse division;

    protected ValueStreamResponse valueStream;

    protected Set<SkillTemplateResponse> skillTemplate;

    public TeamResponse(Team team, Set<SkillTemplateResponse> skillTemplateResponse) {
        setId(team.getId());
        setName(team.getName());
        setDepartment(new DepartmentResponse(team.getDepartment()));
        setDivision(new DivisionResponse(team.getDepartment().getDivision()));
        setValueStream(team.getValueStream().map(ValueStreamResponse::new).orElse(null));
        setSkillTemplate(skillTemplateResponse);
    }

    public TeamResponse(Team team) {
        this(team, new HashSet<>());
    }
}
