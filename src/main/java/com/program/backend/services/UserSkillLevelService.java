package com.program.backend.services;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.SkillLevel;
import com.program.backend.beans.entity.UserSkill;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.enums.SkillStatus;
import com.program.backend.beans.enums.Status;
import com.program.backend.beans.request.AssignSkillLevelRequest;
import com.program.backend.repositories.UserSkillLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.program.backend.beans.enums.Status.APPROVED;

@Service
public class UserSkillLevelService {

    @Autowired
    private UserSkillLevelRepository userSkillLevelRepository;

    @Autowired
    private SkillLevelService skillLevelService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserService userService;

    public UserSkillLevel getCurrentUserSkillLevelByUserIdAndSkillId(Long userId, Long skillId) {
        UserSkill userSkill = userSkillService.getUserSkillByUserIdAndSkillId(userId, skillId);
        UserSkillLevel userSkillLevel =
                userSkillLevelRepository.findTopByUserSkillAndStatusOrderByValidFromDesc(userSkill, APPROVED);
        if (userSkillLevel == null)
            throw new RuntimeException("user skill level not found");
        return userSkillLevel;
    }

    public UserSkillLevel addDefaultUserSkillLevel(UserSkill userSkill) {
        UserSkillLevel userSkillLevel = new UserSkillLevel(userSkill, skillLevelService.getDefault());
        userSkillLevelRepository.save(userSkillLevel);
        return userSkillLevel;
    }

    public UserSkillLevel addUserSkillLevel(UserSkill userSkill, AssignSkillLevelRequest assignSkillLevelRequest) {
        UserSkillLevel userSkillLevel = new UserSkillLevel(userSkill,
                skillLevelService.getByLevel(assignSkillLevelRequest.getLevelId()));
        userSkillLevel.setMotivation(assignSkillLevelRequest.getMotivation());
        userSkillLevel.setPending();
        return userSkillLevelRepository.save(userSkillLevel);
    }

    public UserSkillLevel assignSkillLevelToUser(Long userId, UserSkill userSkill) {
        if (userSkill.getSkillStatus() == SkillStatus.ACTIVE)
            throw new RuntimeException();
        else {
            userSkill.setSkillStatus(SkillStatus.ACTIVE);
            return getCurrentUserSkillLevelByUserIdAndSkillId(userId, userSkill.getSkill().getId());
        }
    }

    public UserSkillLevel createNewUserSkillLevel(Long userId, Skill skill) {
        UserSkill userSkill = new UserSkill(userService.getUserById(userId), skill);
        UserSkillLevel userSkillLevel = addDefaultUserSkillLevel(userSkill);
        userSkill.addUserSkillLevel(userSkillLevel);
        return userSkillLevel;
    }

    public boolean isLatestUserSkillLevelPending(Long userId, Long skillId) {
        UserSkill userSkill = userSkillService.getUserSkillByUserIdAndSkillId(userId, skillId);
        UserSkillLevel latestPendingUserSkillLevel =
                userSkillLevelRepository.findTopByUserSkillAndStatusOrderByValidFromDesc(userSkill, Status.PENDING);

        if (latestPendingUserSkillLevel == null)
            return false;

        UserSkillLevel currentUserSkillLevel = getCurrentUserSkillLevelByUserIdAndSkillId(userId, skillId);
        return !currentUserSkillLevel.getValidFrom().after(latestPendingUserSkillLevel.getValidFrom());
    }

    public Set<UserSkillLevel> getAllApprovedUserSkillLevelsBySkillLevels(Iterable<SkillLevel> skillLevels) {
        Set<UserSkillLevel> userSkillLevels = new HashSet<>();
        for (SkillLevel skillLevel : skillLevels) {
            Iterable<UserSkillLevel> oneLevelSkillLevels = getAllByOneSkillLevelAndStatus(skillLevel, APPROVED);
            oneLevelSkillLevels.forEach(userSkillLevels::add);
        }

        return userSkillLevels;
    }

    public Iterable<UserSkillLevel> getAllByOneSkillLevelAndStatus(SkillLevel skillLevel, Status status) {
        return userSkillLevelRepository.findAllBySkillLevelAndStatus(skillLevel, status);
    }

    public List<UserSkillLevel> getMySkillLevels(UserSkill skill) {
        return userSkillLevelRepository.findAllByUserSkillAndMotivationNotNull(skill);
    }
}
