package com.program.backend.beans.response;

import com.program.backend.beans.entity.Reviewer;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.response.user.UserResponse;
import lombok.Data;

@Data
public class ReviewResponse extends Response {

    private UserResponse user;

    private String message;

    public ReviewResponse(Reviewer reviewer, User user) {
        setUser(new UserResponse(user));
        setMessage(reviewer.getMessage());
    }
}
