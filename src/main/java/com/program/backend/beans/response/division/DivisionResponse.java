package com.program.backend.beans.response.division;

import com.program.backend.beans.entity.Division;
import com.program.backend.beans.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class DivisionResponse extends Response {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    public DivisionResponse(@NonNull Division division) {
        setId(division.getId());
        setName(division.getName());
    }
}
