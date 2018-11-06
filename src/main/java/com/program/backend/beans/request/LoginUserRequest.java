package com.program.backend.beans.request;

import com.program.backend.beans.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginUserRequest {

    @NotNull(message = "Email is required!")
    @Email(message = "Not an email!")
    private String email;

    @NotNull(message = "Password is required!")
    @Size(min = 8, max = 45, message = "Password is too short or too long!")
    private String password;


    public LoginUserRequest(User user) {
        setPassword(user.getPassword());
        setEmail(user.getEmail());
    }
}
