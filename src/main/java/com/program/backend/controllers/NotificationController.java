package com.program.backend.controllers;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.enums.Status;
import com.program.backend.beans.request.NotificationAnswerRequest;
import com.program.backend.beans.response.ApprovalRequestResponse;
import com.program.backend.beans.response.notification.NotificationResponse;
import com.program.backend.services.NotificationService;
import com.program.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/notification")
public class NotificationController {

    private final NotificationService notificationService;

    private final UserService userService;

    @Autowired
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NotificationResponse> getNotificationByUserId(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<NotificationResponse> notificationResponses = notificationService
                .getNotificationResponses(notificationService.getNotificationsByUser(user));
        return notificationResponses
                .stream()
                .filter(t -> t.getStatus().equals(Status.NEW.toString()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public @ResponseBody
    NotificationResponse approvalRequest(@Valid @RequestBody NotificationAnswerRequest notificationAnswerRequest) {
        User user = userService.getUserById(notificationAnswerRequest.getUserId());
        return notificationService.handleRequest(notificationAnswerRequest, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<ApprovalRequestResponse> getUserApprovalNotifications(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        List<ApprovalRequestResponse> list = notificationService.getMyRequestNotifications(user);
        list.sort(Comparator.comparing(ApprovalRequestResponse::getCreationTime)
                .reversed());
        return list;
    }
}
