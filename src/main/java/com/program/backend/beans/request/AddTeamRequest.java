package com.program.backend.beans.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddTeamRequest {

    @NotNull(message = "Team name is required!")
    private String name;

    @NotNull(message = "Department id is required!")
    private Long departmentId;

    @NotNull
    private List<Long> userIds;

    private List<Long> skillIds;

    private Long streamId;
}
