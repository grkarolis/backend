package com.program.backend.beans.request;

import com.program.backend.beans.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NotificationAnswerRequest {

    @NotNull(message = "Notification ID is required!")
    private Long notificationId;

    private String message;

    private Status status;

    private Long userId;
}
