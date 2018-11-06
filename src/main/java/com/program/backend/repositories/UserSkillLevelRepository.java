package com.program.backend.repositories;

import com.program.backend.beans.entity.SkillLevel;
import com.program.backend.beans.entity.UserSkill;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;

@Repository
public interface UserSkillLevelRepository extends CrudRepository<UserSkillLevel, Level> {

    UserSkillLevel findTopByUserSkillAndStatusOrderByValidFromDesc(UserSkill userSkill, Status status);

    Iterable<UserSkillLevel> findAllBySkillLevelAndStatus(SkillLevel skillLevel, Status status);

    List<UserSkillLevel> findAllByUserSkillAndMotivationNotNull(UserSkill skill);
}
