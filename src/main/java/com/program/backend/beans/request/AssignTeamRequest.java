package com.program.backend.beans.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AssignTeamRequest {

    @NotNull(message = "User id is required!")
    private Long userId;

    @NotNull(message = "Team is is required!")
    private Long teamId;

    @NotNull
    private Long loggedUserId;
}
