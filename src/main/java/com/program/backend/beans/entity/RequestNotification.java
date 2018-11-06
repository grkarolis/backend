package com.program.backend.beans.entity;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.enums.Status;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class RequestNotification {

    private Status status = Status.NEW;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long receiverId;

    @ManyToOne
    @NonNull
    private ApprovalRequest approvalRequest;

    @CreationTimestamp
    private LocalDateTime creationTime;

    public RequestNotification(Long receiverId, ApprovalRequest approvalRequest) {
        this.receiverId = receiverId;
        this.approvalRequest = approvalRequest;
    }

    public final void setApproved() {
        this.status = Status.APPROVED;
    }

    public final void setDisapproved() {
        this.status = Status.DISAPPROVED;
    }

    public final void setPending() {
        this.status = Status.PENDING;
    }

    public final void setExpired() {
        this.status = Status.EXPIRED;
    }

    public final String getStatusAsString() {
        return status.toString();
    }

}
