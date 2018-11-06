package com.program.backend.repositories;

import com.program.backend.beans.entity.SkillHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillHeaderRepository extends CrudRepository<SkillHeader, Long> {

    SkillHeader findByTitle(String title);
}
