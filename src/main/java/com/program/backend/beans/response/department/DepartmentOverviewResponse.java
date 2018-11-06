package com.program.backend.beans.response.department;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.program.backend.beans.response.Response;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentOverviewResponse extends Response {

    @JsonUnwrapped
    @NotNull
    private DepartmentResponse departmentResponse;

    @NotNull
    private Set<UserResponse> users;

    @NotNull
    private Set<SkillTemplateResponse> skillTemplate;

    public DepartmentOverviewResponse(DepartmentResponse departmentResponse, Set<UserResponse> users,
                                      Set<SkillTemplateResponse> skillTemplate) {
        setDepartmentResponse(departmentResponse);
        setUsers(users);
        setSkillTemplate(skillTemplate);
    }
}
