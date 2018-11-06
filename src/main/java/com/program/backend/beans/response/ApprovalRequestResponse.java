package com.program.backend.beans.response;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class ApprovalRequestResponse extends Response {

    private Long id;

    private Status status = Status.PENDING;

    private SkillEntityResponse userSkill;

    private MyNotificationReviewers reviewers = new MyNotificationReviewers();

    private Date creationTime;

    private String motivation;

    private String skillLevel;

    public ApprovalRequestResponse(ApprovalRequest request) {
        setId(request.getId());
        setStatus(request.getStatus());
        setUserSkill(new SkillEntityResponse(request.getUserSkillLevel().getUserSkill().getSkill()));
        setCreationTime(request.getCreationTime());
        setMotivation(request.getMotivation());
        setSkillLevel(request.getUserSkillLevel().getSkillLevel().getTitle());
    }
}
