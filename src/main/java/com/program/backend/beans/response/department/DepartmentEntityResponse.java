package com.program.backend.beans.response.department;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.program.backend.beans.entity.Department;
import com.program.backend.beans.response.Response;
import com.program.backend.beans.response.team.TeamResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class DepartmentEntityResponse extends Response {

    @NotNull
    @JsonUnwrapped
    private DepartmentResponse departmentResponse;

    @NonNull
    private List<TeamResponse> teams;

    public DepartmentEntityResponse(Department department) {
        setDepartmentResponse(new DepartmentResponse(department));
        setTeams(department.getTeams().stream().map(TeamResponse::new).collect(Collectors.toList()));
    }
}
