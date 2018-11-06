package com.program.backend.events;

import com.program.backend.beans.entity.Team;
import lombok.Data;
import lombok.NonNull;

@Data
public class TeamUpdatedEvent {

    @NonNull
    private Team team;
}
