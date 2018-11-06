package com.program.backend.exceptions.user;

import com.program.backend.exceptions.MainException;
import org.springframework.http.HttpStatus;

public class UserCouldNotBeRegistered extends MainException {

    public UserCouldNotBeRegistered() {
        setMessageCode("user_could_not_be_registered");
        setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
