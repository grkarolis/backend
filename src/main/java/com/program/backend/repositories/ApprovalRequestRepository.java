package com.program.backend.repositories;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.entity.UserSkillLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRequestRepository extends CrudRepository<ApprovalRequest, Long> {

    ApprovalRequest findByUserSkillLevel(UserSkillLevel userSkillLevel);

    ApprovalRequest findOneById(Long id);

    List<ApprovalRequest> findAllByUserSkillLevel(UserSkillLevel skillLevel);
}
