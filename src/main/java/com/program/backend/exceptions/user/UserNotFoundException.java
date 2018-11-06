package com.program.backend.exceptions.user;

import com.program.backend.exceptions.MainException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MainException {

    public UserNotFoundException() {
        setMessageCode("user_not_found");
        setStatusCode(HttpStatus.NOT_FOUND);
    }
}
