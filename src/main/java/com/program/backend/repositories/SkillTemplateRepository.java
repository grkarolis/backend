package com.program.backend.repositories;

import com.program.backend.beans.entity.SkillTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTemplateRepository extends CrudRepository<SkillTemplate, Long> {

    SkillTemplate findOneByTeamId(Long id);
}
