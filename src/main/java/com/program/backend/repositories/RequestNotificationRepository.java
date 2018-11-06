package com.program.backend.repositories;

import com.program.backend.beans.request.ApprovalRequest;
import com.program.backend.beans.entity.RequestNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestNotificationRepository extends CrudRepository<RequestNotification, Long> {

    Iterable<RequestNotification> findByReceiverId(Long id);

    Iterable<RequestNotification> findByApprovalRequest(ApprovalRequest approvalRequest);

    RequestNotification findRequestNotificationById(Long id);

}
