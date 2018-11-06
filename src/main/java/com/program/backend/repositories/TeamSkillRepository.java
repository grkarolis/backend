package com.program.backend.repositories;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.Team;
import com.program.backend.beans.entity.TeamSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSkillRepository extends CrudRepository<TeamSkill, Long> {

    TeamSkill findTopByTeamAndSkill(Team team, Skill skill);
}
