package com.program.backend.exceptions.user;

import com.program.backend.exceptions.MainException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends MainException {

    public UserAlreadyExistsException() {
        setMessageCode("user_already_exists");
        setStatusCode(HttpStatus.NOT_FOUND);
    }
}
