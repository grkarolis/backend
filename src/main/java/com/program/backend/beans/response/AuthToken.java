package com.program.backend.beans.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthToken extends Response {

    private String token;

    public AuthToken(String token) {
        setToken(token);
    }
}
