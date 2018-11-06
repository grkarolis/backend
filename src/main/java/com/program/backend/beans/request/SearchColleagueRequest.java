package com.program.backend.beans.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SearchColleagueRequest {

    @NotNull
    private Long userId;

    private String query;
}
