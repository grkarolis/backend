package com.program.backend.beans.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class AssignSkillLevelRequest {

    @NotNull(message = "Level ID is required!")
    @NonNull
    private Long levelId;

    @NotNull(message = "Skill ID is required!")
    @NonNull
    private Long skillId;

    @NotNull(message = "Motivation is required!")
    @NonNull
    private String motivation;

    @NotNull(message = "User ID is required!")
    @NonNull
    private Long userId;
}
