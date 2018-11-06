package com.program.backend.beans.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthenticationError extends Response {

    @NonNull
    private String field;

    @NonNull
    private String message;
}
