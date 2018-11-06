package com.program.backend.beans.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
public class UpdateTeamRequest {

    @NotNull(message = "Team title is required!")
    private String name;

    @NotNull(message = "Department id is required!")
    private Long departmentId;

    @NotNull
    private List<Long> userIds;

    @NotNull
    private List<Long> skillIds;

    private Long streamId;

    @NotNull
    private Long userId;

}
