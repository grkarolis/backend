package com.program.backend.repositories;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.entity.UserSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends CrudRepository<UserSkill, Long> {

    UserSkill findByUserIdAndSkillId(Long userId, Long skillId);

    Iterable<UserSkill> findAllBySkill(Skill skill);

    List<UserSkill> findAllByUser(User user);
}
