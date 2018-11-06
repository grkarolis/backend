package com.program.backend.services;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.entity.*;
import com.program.backend.beans.request.AssignSkillLevelRequest;
import com.program.backend.events.UserSkillLevelRaisedEvent;
import com.program.backend.repositories.ApprovalRequestRepository;
import com.program.backend.repositories.ReviewersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;

    @Autowired
    private ReviewersRepository reviewersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private SkillLevelService skillLevelService;

    @Autowired
    private UserSkillLevelService userSkillLevelService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public static final Integer APPROVES_NEEDED_TO_LEVEL_UP = 2;

    public static final Integer DISAPPROVES_NEEDED_TO_REJECT_LEVEL_UP = 1;

    public static final Integer MINIMUM_NUMBERS_OF_USERS_TO_NOTIFY = 5;

    public ApprovalRequest addSkillLevelApprovalRequestWithNotifications(Long userId, AssignSkillLevelRequest assignSkillLevelRequest) {

        if (userSkillLevelService.isLatestUserSkillLevelPending(userId, assignSkillLevelRequest.getSkillId())) {
            throw new RuntimeException();
        }
        if (assignSkillLevelRequest.getLevelId() - userSkillLevelService
                .getCurrentUserSkillLevelByUserIdAndSkillId(userId, assignSkillLevelRequest.getSkillId())
                .getSkillLevel().getLevel() > 1) {
            throw new RuntimeException("too high skill level request");
        }

        UserSkill userSkill = userSkillService.getUserSkillByUserIdAndSkillId(userId, assignSkillLevelRequest.getSkillId());
        UserSkillLevel newUserSkillLevel = userSkillLevelService.addUserSkillLevel(userSkill, assignSkillLevelRequest);

        ApprovalRequest approvalRequest = new ApprovalRequest();
        approvalRequest.setUserSkillLevel(newUserSkillLevel);
        approvalRequest.setMotivation(assignSkillLevelRequest.getMotivation());

        approvalRequest.setRequestNotifications(createNotificationsForUsersWithTheSameSkill(userId, assignSkillLevelRequest, approvalRequest));
        approvalRequestRepository.save(approvalRequest);
        return approvalRequest;
    }

    private List<RequestNotification> createNotificationsForUsersWithTheSameSkill(Long userId, AssignSkillLevelRequest assignSkillLevelRequest, ApprovalRequest approvalRequest) {

        Iterable<SkillLevel> skillLevels = skillLevelService.getAllByLevelGreaterThanOrEqual(assignSkillLevelRequest.getLevelId());
        Iterable<UserSkillLevel> userSkillLevels = userSkillLevelService.getAllApprovedUserSkillLevelsBySkillLevels(skillLevels);

        List<User> usersToBeNotified = new LinkedList<>();
        Long skillIdFromRequest = approvalRequest.getUserSkillLevel().getUserSkill().getId();
        for (UserSkillLevel userSkillLevel : userSkillLevels) {
            Long skillId = userSkillLevel.getUserSkill().getSkill().getId();
            User user = userSkillLevel.getUserSkill().getUser();
            if (skillId.equals(skillIdFromRequest) && !user.getId().equals(userId))
                usersToBeNotified.add(user);
        }

        if (usersToBeNotified.size() < MINIMUM_NUMBERS_OF_USERS_TO_NOTIFY)
            usersToBeNotified = (List<User>) userService.getAllUsers();

        List<RequestNotification> notifications = new LinkedList<>();
        usersToBeNotified.stream()
                .filter(user -> !user.getId().equals(userId))
                .forEach(user -> notifications.add(new RequestNotification(user.getId(), approvalRequest)));
        return notifications;
    }

    public boolean isUserAlreadyApprovedRequest(User user, ApprovalRequest approvalRequest) {
        for (Reviewer reviewer : approvalRequest.getApprovers()) {
            if (userService.getUserById(reviewer.getUserId()).equals(user))
                return true;
        }

        return false;
    }

    public boolean isUserAlreadyDisapprovedRequest(User user, ApprovalRequest approvalRequest) {
        for (Reviewer disapprover : approvalRequest.getDisapprovers()) {
            if (userService.getUserById(disapprover.getUserId()).equals(user))
                return true;
        }
        return false;
    }

    public void removeUserFromApproversAndDisapproversIfExists(ApprovalRequest approvalRequest, User user) {
        if (user == null)
            throw new RuntimeException("user not found");
        if (isUserAlreadyDisapprovedRequest(user, approvalRequest))
            removeDissapproversFromApprovalRequest(user, approvalRequest);
        else if (isUserAlreadyApprovedRequest(user, approvalRequest))
            removeApproversFromApprovalRequest(user, approvalRequest);
    }

    public ApprovalRequest approve(String message, ApprovalRequest approvalRequest, User user) {
        removeUserFromApproversAndDisapproversIfExists(approvalRequest, user);
        Reviewer approver = new Reviewer(user.getId(), message);
        reviewersRepository.save(approver);
        approvalRequest.addApprover(approver);

        if (approvalRequest.getApprovers().size() >= APPROVES_NEEDED_TO_LEVEL_UP) {
            approvalRequest.setApproved();
            applicationEventPublisher.publishEvent(new UserSkillLevelRaisedEvent(approvalRequest.getUserSkillLevel()));
        }

        return approvalRequestRepository.save(approvalRequest);
    }

    public ApprovalRequest dissapprove(String message, ApprovalRequest approvalRequest, User user) {
        removeUserFromApproversAndDisapproversIfExists(approvalRequest, user);
        Reviewer disapproved = new Reviewer(user.getId(), message);
        reviewersRepository.save(disapproved);
        approvalRequest.addDisapprover(disapproved);

        if (approvalRequest.getDisapprovers().size() >= DISAPPROVES_NEEDED_TO_REJECT_LEVEL_UP)
            approvalRequest.setDisapproved();

        return approvalRequestRepository.save(approvalRequest);
    }

    public ApprovalRequest getApprovalRequestByRequestNotification(RequestNotification requestNotification) {
        return approvalRequestRepository.findOneById(requestNotification.getApprovalRequest().getId());
    }

    public ApprovalRequest update(ApprovalRequest approvalRequest) {
        return approvalRequestRepository.save(approvalRequest);
    }

    private void removeApproversFromApprovalRequest(User user, ApprovalRequest approvalRequest) {
        if (user == null)
            throw new RuntimeException("user not found");

        Reviewer currentApprover = getApproverById(approvalRequest.getApprovers().stream()
                .filter(approver -> userService.getUserById(approver.getUserId()).equals(user))
                .findFirst()
                .get().getId());

        approvalRequest.getApprovers().remove(currentApprover);
        reviewersRepository.delete(currentApprover);
    }

    public void removeDissapproversFromApprovalRequest(User user, ApprovalRequest approvalRequest) {
        if (user == null)
            throw new RuntimeException("user not found");

        Reviewer currentDisapprover = getDisapproverById(approvalRequest.getDisapprovers().stream()
                .filter(disapprover -> userService.getUserById(disapprover.getUserId()).equals(user))
                .findFirst()
                .get().getId());

        approvalRequest.getDisapprovers().remove(currentDisapprover);
        reviewersRepository.delete(currentDisapprover);
    }

    public Reviewer saveApprover(Reviewer approver) {
        return reviewersRepository.save(approver);
    }

    public Reviewer saveDisapprover(Reviewer disapprover) {
        return reviewersRepository.save(disapprover);
    }

    public Reviewer getDisapproverById(Long id) {
        Reviewer disapprover = reviewersRepository.findOneById(id);
        if (disapprover == null)
            throw new RuntimeException("disapprover not found");

        return disapprover;
    }

    public Reviewer getApproverById(Long id) {
        Reviewer approver = reviewersRepository.findOneById(id);
        if (approver == null)
            throw new RuntimeException("approver not found");

        return approver;
    }

    public ApprovalRequest getMyRequest(UserSkillLevel skillLevel) {
        return approvalRequestRepository.findByUserSkillLevel(skillLevel);
    }
}
