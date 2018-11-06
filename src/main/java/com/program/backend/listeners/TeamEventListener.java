package com.program.backend.listeners;

import com.program.backend.events.TeamAddedEvent;
import com.program.backend.events.TeamUpdatedEvent;
import com.program.backend.services.TeamSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TeamEventListener {

    @Autowired
    private TeamSkillService teamSkillService;

    @EventListener
    public void onTeamAddedEvent(TeamAddedEvent event) {
        teamSkillService.createTeamSkills(event.getTeam());
    }

    @EventListener
    public void onTeamUpdatedEvent(TeamUpdatedEvent event) {
        teamSkillService.updateTeamSkills(event.getTeam());
    }
}
