package com.program.backend.beans.response.notification;

import com.program.backend.beans.entity.RequestNotification;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.enums.NotificationType;
import com.program.backend.beans.response.MyNotificationReviewers;
import com.program.backend.beans.response.ReviewResponse;
import com.program.backend.beans.response.SkillEntityResponse;
import com.program.backend.beans.response.user.UserResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Data
public class NotificationResponse {

    private NotificationType type;

    private Timestamp date;

    private String status;

    private Long id;

    private String message;

    private UserResponse sender;

    private String skillLevel;

    private SkillEntityResponse skill;

    private MyNotificationReviewers reviewers;

    public NotificationResponse(RequestNotification requestNotification) {
        User user = requestNotification.getApprovalRequest().getUserSkillLevel().getUserSkill().getUser();
        UserSkillLevel userSkillLevel = requestNotification.getApprovalRequest().getUserSkillLevel();
        setId(requestNotification.getId());
        setSkill(new SkillEntityResponse(userSkillLevel.getUserSkill().getSkill()));
        setMessage(requestNotification.getApprovalRequest().getMotivation());
        setSender(new UserResponse(user));
        setSkillLevel(userSkillLevel.getSkillLevel().getTitle());
        setDate(Timestamp.valueOf(requestNotification.getCreationTime()));
        setStatus(requestNotification.getStatusAsString());
        setType(NotificationType.PENDING);
    }

    public NotificationResponse(RequestNotification requestNotification, MyNotificationReviewers reviewResponses, NotificationType type) {
        this(requestNotification);
        setType(type);
        setReviewers(reviewResponses);
    }
}
