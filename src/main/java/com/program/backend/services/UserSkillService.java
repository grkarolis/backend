package com.program.backend.services;

import com.program.backend.beans.entity.*;
import com.program.backend.beans.enums.SkillStatus;
import com.program.backend.beans.enums.Status;
import com.program.backend.beans.request.AddSkillRequest;
import com.program.backend.beans.response.user.skill.ColleagueUserSkillResponse;
import com.program.backend.beans.response.user.skill.NonColleagueUserSkillResponse;
import com.program.backend.beans.response.user.skill.UserSkillResponse;
import com.program.backend.events.UserSkillAddedEvent;
import com.program.backend.events.UserSkillRemovedEvent;
import com.program.backend.repositories.TeamRepository;
import com.program.backend.repositories.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserSkillLevelService userSkillLevelService;

    @Autowired
    private SkillTemplateService skillTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<UserSkillResponse> getProfileUserSkills(List<UserSkill> userSkills) {
        return userSkills.stream().map(userSkill -> new ColleagueUserSkillResponse(userSkill.getSkill(),
                getCurrentSkillLevelStatus(userSkill))).collect(Collectors.toList());
    }

    public void sortUserSkillLevels(UserSkill userSkill) {
        userSkill.getUserSkillLevels().sort((o1, o2) -> o2.getValidFrom().compareTo(o1.getValidFrom()));
    }

    public UserSkillLevel getCurrentSkillLevelStatus(UserSkill userSkill) {
        sortUserSkillLevels(userSkill);
        Collections.reverse(userSkill.getUserSkillLevels());
        Iterator<UserSkillLevel> userSkillLevelIterator = userSkill.getUserSkillLevels().iterator();
        UserSkillLevel currentUserSkillLevel = new UserSkillLevel();
        while (userSkillLevelIterator.hasNext()) {
            UserSkillLevel userSkillLevel = userSkillLevelIterator.next();
            if (userSkillLevel.getStatus() != Status.DISAPPROVED) {
                currentUserSkillLevel = userSkillLevel;
            }
        }
        return currentUserSkillLevel;
    }

    public UserSkill getUserSkillByUserIdAndSkillId(Long userId, Long skillId) {
        UserSkill userSkill = userSkillRepository.findByUserIdAndSkillId(userId, skillId);
        if (userSkill == null)
            throw new RuntimeException();
        return userSkill;
    }

    public ColleagueUserSkillResponse addUserSkill(Long userId, AddSkillRequest addSkillRequest) {
        Skill skill = skillService.getSkillFromAddSkillRequest(addSkillRequest);
        UserSkillLevel userSkillLevel = getUserSkillLevel(userId, skill);
        UserSkill updatedUserSkill = userSkillRepository.save(userSkillLevel.getUserSkill());
        applicationEventPublisher.publishEvent(new UserSkillAddedEvent(updatedUserSkill));
        User user = userService.getUserById(userId);
        if (user.getTeam().isPresent()) {
            Team team = teamRepository.findTeamById(user.getTeam().get().getId());
            List<Skill> skills = team.getSkillTemplate().getSkills();
            skills.add(updatedUserSkill.getSkill());
            skills = skills.stream()
                    .distinct().collect(Collectors.toList());
            team.setSkillTemplate(skillTemplateService.createOrUpdateSkillTemplate(team, skills));
            teamRepository.save(team);
        }

        return new ColleagueUserSkillResponse(updatedUserSkill.getSkill(), userSkillLevel);
    }

    private UserSkillLevel getUserSkillLevel(Long id, Skill skill) {
        Optional<UserSkill> userSkill = Optional.ofNullable(userSkillRepository.findByUserIdAndSkillId(id, skill.getId()));
        if (userSkill.isPresent())
            return userSkillLevelService.assignSkillLevelToUser(id, userSkill.get());
        else
            return userSkillLevelService.createNewUserSkillLevel(id, skill);
    }

    public UserSkillResponse removeUserSkill(Long userId, Long skillId) {
        Skill skill = skillService.getSkillById(skillId);
        UserSkill userSkill = userSkillRepository.findByUserIdAndSkillId(userId, skillId);

        if (userSkill == null)
            throw new RuntimeException();

        if (!userSkillLevelService.isLatestUserSkillLevelPending(userId, userSkill.getSkill().getId())) {
            userSkill.setSkillStatus(SkillStatus.INACTIVE);
            userSkillRepository.save(userSkill);
        } else {
            throw new RuntimeException();
        }

        applicationEventPublisher.publishEvent(new UserSkillRemovedEvent(userSkill));

        return new UserSkillResponse(userSkill.getSkill());
    }

    public Iterable<UserSkill> getUserSkillsBySkill(Skill skill) {
        return userSkillRepository.findAllBySkill(skill);
    }

    public List<UserSkillResponse> getNonColleagueUserSkillResponseList(List<UserSkill> userSkills) {
        return userSkills.stream().map(userSkill -> new NonColleagueUserSkillResponse(userSkill.getSkill(),
                getCurrentSkillLevel(userSkill))).collect(Collectors.toList());
    }

    public List<UserSkillResponse> getNormalUserSkillResponseList(List<UserSkill> userSkills) {
        return userSkills.stream().map(userSkill -> new ColleagueUserSkillResponse(userSkill.getSkill(),
                getCurrentSkillLevel(userSkill))).collect(Collectors.toList());
    }

    public UserSkillLevel getCurrentSkillLevel(UserSkill userSkill) {
        sortUserSkillLevels(userSkill);
        Collections.reverse(userSkill.getUserSkillLevels());
        UserSkillLevel currentUserSkillLevel = null;
        for (UserSkillLevel level : userSkill.getUserSkillLevels())
            if (level.getStatus() == Status.APPROVED)
                currentUserSkillLevel = level;

        return currentUserSkillLevel;
    }

    public List<UserSkill> getMySkills(User user) {
        return userSkillRepository.findAllByUser(user);
    }
}
