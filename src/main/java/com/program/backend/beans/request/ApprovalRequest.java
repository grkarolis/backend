package com.program.backend.beans.request;

import com.program.backend.beans.entity.RequestNotification;
import com.program.backend.beans.entity.Reviewer;
import com.program.backend.beans.entity.UserSkillLevel;
import com.program.backend.beans.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"approvers", "disapprovers", "requestNotifications"})
@ToString(exclude = {"approvers", "disapprovers", "requestNotifications"})
public class ApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Status status = Status.PENDING;

    @OneToOne(cascade = {CascadeType.ALL})
    private UserSkillLevel userSkillLevel;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<Reviewer> approvers = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<Reviewer> disapprovers = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<RequestNotification> requestNotifications;

    @CreationTimestamp
    private Date creationTime;

    private String motivation;

    public void addApprover(Reviewer approver) {
        approvers.add(approver);
    }

    public void addDisapprover(Reviewer disapprover) {
        disapprovers.add(disapprover);
    }

    public void setRequestNotification(RequestNotification requestNotification) {
        this.requestNotifications = new LinkedList<>();
        this.requestNotifications.add(requestNotification);
    }

    public void setPending() {
        this.status = Status.PENDING;
        userSkillLevel.setPending();
    }

    public void setApproved() {
        this.status = Status.APPROVED;
        userSkillLevel.setApproved();
    }

    public void setDisapproved() {
        this.status = Status.DISAPPROVED;
        userSkillLevel.setDisapproved();
    }
}
