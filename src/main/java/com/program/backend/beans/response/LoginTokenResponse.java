package com.program.backend.beans.response;

import com.auth0.json.auth.TokenHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginTokenResponse extends Response {

    private String access_token;
    private String id_token;

    public LoginTokenResponse(TokenHolder tokenHolder) {
        this.access_token = tokenHolder.getAccessToken();
        this.id_token = tokenHolder.getIdToken();
    }
}
