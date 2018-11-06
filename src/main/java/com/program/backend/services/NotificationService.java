package com.program.backend.services;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.entity.*;
import com.program.backend.beans.enums.NotificationType;
import com.program.backend.beans.enums.Status;
import com.program.backend.beans.request.NotificationAnswerRequest;
import com.program.backend.beans.response.ApprovalRequestResponse;
import com.program.backend.beans.response.MyNotificationReviewers;
import com.program.backend.beans.response.ReviewResponse;
import com.program.backend.beans.response.notification.NotificationResponse;
import com.program.backend.repositories.RequestNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private RequestNotificationRepository requestNotificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserSkillLevelService userSkillLevelService;

    public Integer APPROVES_NEEDED = 2;

    public Integer DISAPPROVES_NEEDED = 1;

    public Iterable<RequestNotification> getNotificationsByUser(User user) {
        return requestNotificationRepository.findByReceiverId(user.getId());
    }

    public Iterable<RequestNotification> setNotificationAsExpired(Iterable<RequestNotification> requestNotifications) {
        for (RequestNotification requestNotification : requestNotifications) {
            requestNotification.setExpired();
            requestNotificationRepository.save(requestNotification);
        }

        return requestNotifications;
    }

    public NotificationResponse handleRequest(NotificationAnswerRequest notificationAnswerRequest, User user) {
        RequestNotification requestNotification = getNotificationById(notificationAnswerRequest.getNotificationId());
        ApprovalRequest approvalRequest = approvalService.getApprovalRequestByRequestNotification(requestNotification);
        MyNotificationReviewers reviewResponses = new MyNotificationReviewers();
        switch (approvalRequest.getStatus()) {
            case PENDING:
                requestNotification = changeNotificationRequestStatus(approvalRequest, requestNotification, user, notificationAnswerRequest);
                break;
            case APPROVED:
                requestNotification.setExpired();
                requestNotificationRepository.save(requestNotification);
                reviewResponses.setApprovers(getReviewersResponseList(approvalRequest.getApprovers()));
                reviewResponses.setDisapprovers(getReviewersResponseList(approvalRequest.getDisapprovers()));
                return new NotificationResponse(requestNotification, reviewResponses, NotificationType.APPROVED);
            case DISAPPROVED:
                requestNotification.setExpired();
                requestNotificationRepository.save(requestNotification);
                reviewResponses.setDisapprovers(getReviewersResponseList(approvalRequest.getDisapprovers()));
                reviewResponses.setApprovers(getReviewersResponseList(approvalRequest.getApprovers()));
                return new NotificationResponse(requestNotification, reviewResponses, NotificationType.DISAPPROVED);
        }
        return new NotificationResponse(requestNotification);
    }

    public List<ReviewResponse> getReviewersResponseList(List<Reviewer> reviewers) {
        try {
            return reviewers.stream()
                    .map(reviewer -> new ReviewResponse(reviewer, userService.getUserById(reviewer.getUserId())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("something happened getting notifications");
        }
    }

    public List<NotificationResponse> getNotificationResponses(Iterable<RequestNotification> requestNotifications) {
        List<NotificationResponse> requestNotificationResponses = new LinkedList<>();
        for (RequestNotification requestNotification : requestNotifications) {
            ApprovalRequest approvalRequest = requestNotification.getApprovalRequest();
            if (approvalRequest.getUserSkillLevel().getUserSkill().getUser().equals(userService.getUserById(requestNotification.getReceiverId()))) {
                MyNotificationReviewers reviewers = new MyNotificationReviewers();
                reviewers.setDisapprovers(getReviewersResponseList(approvalRequest.getDisapprovers()));
                reviewers.setApprovers(getReviewersResponseList(approvalRequest.getApprovers()));
                if (approvalRequest.getStatus() == Status.DISAPPROVED) {
                    requestNotificationResponses.add(new NotificationResponse(requestNotification, reviewers, NotificationType.DISAPPROVED));
                } else if (approvalRequest.getStatus() == Status.APPROVED) {
                    requestNotificationResponses.add(new NotificationResponse(requestNotification, reviewers, NotificationType.APPROVED));
                }
            } else
                requestNotificationResponses.add(new NotificationResponse(requestNotification));
        }
        sortRequestNotificationsByDate(requestNotificationResponses);
        return requestNotificationResponses;
    }

    private void sortRequestNotificationsByDate(List<NotificationResponse> requestNotifications) {
        requestNotifications.sort(Comparator.comparing(NotificationResponse::getDate).reversed());
    }

    public RequestNotification getNotificationById(Long id) {
        if (requestNotificationRepository.findRequestNotificationById(id) == null)
            throw new RuntimeException("no notification exception");

        return requestNotificationRepository.findRequestNotificationById(id);
    }

    private RequestNotification changeNotificationRequestStatus(ApprovalRequest approvalRequest,
                                                                RequestNotification requestNotification, User user,
                                                                NotificationAnswerRequest notificationAnswerRequest) {
        requestNotification.setPending();
        switch (notificationAnswerRequest.getStatus()) {
            case APPROVED:
                requestNotification = approve(approvalRequest, requestNotification, user, notificationAnswerRequest.getMessage());
                break;
            case DISAPPROVED:
                requestNotification = disapprove(approvalRequest, requestNotification, user, notificationAnswerRequest.getMessage());
                break;
            case PENDING:
                requestNotification = setNotificationAsPending(approvalRequest, requestNotification, user);
            default:
                break;
        }
        return requestNotificationRepository.save(requestNotification);
    }

    private RequestNotification approve(ApprovalRequest approvalRequest, RequestNotification requestNotification,
                                        User user, String message) {
        requestNotification.setApproved();
        Integer approves = approvalService.approve(message, approvalRequest, user).getApprovers().size();
        if (approves >= APPROVES_NEEDED) {
            setNotificationAsExpired(approvalRequest.getRequestNotifications());
            sendNotificationAboutSkillLevelStatusChanges(approvalRequest);
        }
        return requestNotification;
    }

    private RequestNotification disapprove(ApprovalRequest approvalRequest, RequestNotification requestNotification,
                                           User user, String message) {
        requestNotification.setDisapproved();
        Integer disapproves = approvalService.dissapprove(message, approvalRequest, user).getDisapprovers().size();
        if (disapproves >= DISAPPROVES_NEEDED) {
            setNotificationAsExpired(approvalRequest.getRequestNotifications());
            sendNotificationAboutSkillLevelStatusChanges(approvalRequest);
        }
        return requestNotification;
    }

    private RequestNotification setNotificationAsPending(ApprovalRequest approvalRequest, RequestNotification requestNotification,
                                                         User user) {
        approvalService.removeUserFromApproversAndDisapproversIfExists(approvalRequest, user);
        requestNotification.setPending();
        return requestNotification;
    }

    private void sendNotificationAboutSkillLevelStatusChanges(ApprovalRequest approvalRequest) {
        approvalRequest.setRequestNotification(new RequestNotification(approvalRequest.getUserSkillLevel().getUserSkill().getId(),
                approvalRequest));
        approvalService.update(approvalRequest);
    }

    public List<ApprovalRequestResponse> getMyRequestNotifications(User user) {
//        List<RequestNotification> notifications = new LinkedList<>();
//        requestNotificationRepository.findAll().forEach(t -> notifications.add(t));
//        List<RequestNotification> requestNotifications = notifications.stream()
//                .filter(t -> t.getApprovalRequest().getUserSkill().getUserSkill().getUser().equals(user))
//                .collect(Collectors.toList());
//        List<NotificationResponse> myNotifications = new LinkedList<>();

        List<UserSkill> mySkills = userSkillService.getMySkills(user);
        List<UserSkillLevel> mySkillLevels = new LinkedList<>();
        for (UserSkill skill : mySkills)
                mySkillLevels.addAll(userSkillLevelService.getMySkillLevels(skill));
        List<ApprovalRequestResponse> myRequests = new LinkedList<>();
        for (UserSkillLevel level : mySkillLevels) {
            ApprovalRequest approvalRequest = approvalService.getMyRequest(level);
            ApprovalRequestResponse request = new ApprovalRequestResponse(approvalRequest);
            List<ReviewResponse> approvers = new LinkedList<>();
            List<ReviewResponse> disapprovers = new LinkedList<>();
            for (Reviewer reviewer : approvalRequest.getDisapprovers()) {
                disapprovers.add(new ReviewResponse(reviewer, userService.getUserById(reviewer.getUserId())));
            }
            for (Reviewer reviewer : approvalRequest.getApprovers()) {
                approvers.add(new ReviewResponse(reviewer, userService.getUserById(reviewer.getUserId())));
            }
            request.getReviewers().setApprovers(approvers);
            request.getReviewers().setDisapprovers(disapprovers);
            myRequests.add(request);
        }

//        for (RequestNotification notification : requestNotifications) {
//            ApprovalRequest approvalRequest = notification.getApprovalRequest();
//            MyNotificationReviewers reviewers = new MyNotificationReviewers();
//            reviewers.setDisapprovers(getReviewersResponseList(approvalRequest.getDisapprovers()));
//            reviewers.setApprovers(getReviewersResponseList(approvalRequest.getApprovers()));
//            if (approvalRequest.getStatus() == Status.DISAPPROVED) {
//                myNotifications.add(new NotificationResponse(notification, reviewers, NotificationType.DISAPPROVED));
//            } else if (approvalRequest.getStatus() == Status.APPROVED) {
//                myNotifications.add(new NotificationResponse(notification, reviewers, NotificationType.APPROVED));
//            } else {
//                myNotifications.add(new NotificationResponse(notification, reviewers, NotificationType.PENDING));
//            }
//        }
        return myRequests;
    }
}
