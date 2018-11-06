package com.program.backend.services;

import com.program.backend.beans.entity.*;
import com.program.backend.repositories.TeamSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class TeamSkillService {

    @Autowired
    private TeamSkillRepository teamSkillRepository;

    @Autowired
    private UserSkillService userSkillService;

    public void createTeamSkills(Team team) {
        List<TeamSkill> teamSkills = new LinkedList<>();
        for (Skill skill : team.getSkillTemplate().getSkills()) {
            TeamSkill teamSkill = new TeamSkill(
                    team, skill, this.countUserSkills(team.getUsers(), skill),
                    this.getUserAverageSkillLevel(team.getUsers(), skill));
            teamSkills.add(teamSkill);
            teamSkillRepository.save(teamSkill);
        }
    }

    public void updateTeamSkills(@NotNull Team team) {
        for (Skill skill : team.getSkillTemplate().getSkills())
            this.updateTeamSkill(team, skill);
    }

    public TeamSkill updateTeamSkill(@NotNull Team team, @NotNull Skill skill) {
        TeamSkill teamSkill = teamSkillRepository.findTopByTeamAndSkill(team ,skill);

        if (teamSkill != null) {
            teamSkill.setSkillCount(this.countUserSkills(team.getUsers(), skill));
            teamSkill.setSkillLevelAverage(this.getUserAverageSkillLevel(team.getUsers(), skill));
        } else {
            teamSkill = new TeamSkill(
                    team, skill, this.countUserSkills(team.getUsers(), skill),
                    this.getUserAverageSkillLevel(team.getUsers(), skill));
        }
        return teamSkillRepository.save(teamSkill);
    }

    public TeamSkill getTeamSkillByTeamAndSkill(@NotNull Team team, @NotNull Skill skill) {
        return teamSkillRepository.findTopByTeamAndSkill(team, skill);
    }

    public Integer getTeamSkillCount(@NotNull Team team, @NotNull Skill skill) {
        TeamSkill teamSkill = teamSkillRepository.findTopByTeamAndSkill(team ,skill);
        return teamSkill.getSkillCount();
    }

    public Double getTeamAverageSkillLevel(@NotNull Team team, @NotNull Skill skill) {
        TeamSkill teamSkill = teamSkillRepository.findTopByTeamAndSkill(team, skill);
        return teamSkill.getSkillLevelAverage();
    }

    private Double getUserAverageSkillLevel(@NotNull List<User> users, @NotNull Skill skill) {
        OptionalDouble average = users.stream()
                .flatMapToDouble(user -> user.getActiveUserSkills().stream()
                .filter(userSkill -> userSkill.getSkill().equals(skill))
                .mapToDouble(userSkill -> userSkillService.getCurrentSkillLevel(userSkill)
                .getSkillLevel().getLevel().doubleValue()))
                .average();
        return average.isPresent() ? average.getAsDouble() : 0d;
    }

    private Integer countUserSkills(@NotNull List<User> users, @NotNull Skill skill) {
        int counter = 0;
        for (User user : users) {
            for (UserSkill userSkill : user.getActiveUserSkills()) {
                if (userSkill.getSkill().equals(skill))
                    counter++;
            }
        }
        return counter;
    }
}
