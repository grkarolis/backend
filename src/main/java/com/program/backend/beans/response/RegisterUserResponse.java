package com.program.backend.beans.response;

import com.program.backend.beans.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterUserResponse extends Response {

    private String name;

    private String surname;

    private String email;

    public RegisterUserResponse(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
    }
}
