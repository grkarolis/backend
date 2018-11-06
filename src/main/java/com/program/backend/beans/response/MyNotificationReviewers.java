package com.program.backend.beans.response;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class MyNotificationReviewers extends Response {

    List<ReviewResponse> approvers = new LinkedList<>();

    List<ReviewResponse> disapprovers = new LinkedList<>();

}
