package com.program.backend.beans.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VoteUserSkillRequest {

    @NotNull(message = "User ID is required!")
    private Long userId;

    @NotNull(message = "Skill ID is required!")
    private Long skillId;

    @NotNull(message = "Message is required!")
    private String message;

    @NotNull(message = "Voter ID is required!")
    private Long voterId;

}
