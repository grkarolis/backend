package com.program.backend.repositories;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.SkillHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    Skill findOneByTitleAndSkillHeader(String title, SkillHeader header);

    Skill findOneById(Long id);
}
