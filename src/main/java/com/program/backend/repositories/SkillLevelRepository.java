package com.program.backend.repositories;

import com.program.backend.beans.entity.SkillLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends CrudRepository<SkillLevel, Long> {

    SkillLevel findByLevel(Long level);

    Iterable<SkillLevel> findAllByLevelGreaterThanEqual(Long level);

    SkillLevel findOneById(Long id);
}
