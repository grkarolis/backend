package com.program.backend.listeners;

import com.program.backend.beans.entity.Team;
import com.program.backend.events.UserSkillAddedEvent;
import com.program.backend.events.UserSkillLevelRaisedEvent;
import com.program.backend.events.UserSkillRemovedEvent;
import com.program.backend.services.NewsFeedService;
import com.program.backend.services.TeamSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSkillEventListener {

    @Autowired
    private TeamSkillService teamSkillService;

    @Autowired
    private NewsFeedService newsFeedService;

    @EventListener
    public void onUserSkillAddedEvent(UserSkillAddedEvent event) {
        Optional<Team> team = event.getUserSkill().getUser().getTeam();
        team.ifPresent(t -> teamSkillService.updateTeamSkill(t,
                event.getUserSkill().getSkill()));
    }

    @EventListener
    public void onUserSkillRemovedEvent(UserSkillRemovedEvent event) {
        Optional<Team> team = event.getUserSkill().getUser().getTeam();
        team.ifPresent(t -> teamSkillService.updateTeamSkill(t,
                event.getUserSkill().getSkill()));
    }

    @EventListener
    public void onUserSkillLevelRaisedEvent(UserSkillLevelRaisedEvent event) {
        Optional<Team> team = event.getUserSkillLevel().getUserSkill().getUser().getTeam();
        team.ifPresent(t -> teamSkillService.updateTeamSkill(t,
                event.getUserSkillLevel().getUserSkill().getSkill()));

        newsFeedService.addSkillLevelRaisedFeed(event.getUserSkillLevel().getUserSkill().getUser().getId(),
                event.getUserSkillLevel().getSkillLevel(),
                event.getUserSkillLevel().getUserSkill().getSkill());
    }


}
