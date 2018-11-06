package com.program.backend.beans.response;

import java.util.LinkedList;
import java.util.List;

public class AuthenticationErrorsWrapper extends Response {

    private List<AuthenticationError> errors;

    public AuthenticationErrorsWrapper() {
        this.errors = new LinkedList<>();
    }

    public List<AuthenticationError> getErrors() {
        return errors;
    }

    public void addErrors(AuthenticationError error) {
        this.errors.add(error);
    }
}
