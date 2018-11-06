package com.program.backend.beans.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class AddSkillRequest {

    @NotNull(message = "Skill title is required!")
    private String title;

    private String header;

    private Long id;

    private Long userId;

    public AddSkillRequest(String title, String header) {
        this.title = title;
        this.header = header;
    }
}
