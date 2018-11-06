package com.program.backend.beans.request;

import com.program.backend.beans.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterUserRequest {

    @NotNull(message = "Name is required!")
    private String name;

    @NotNull(message = "Surname is required!")
    private String surname;

    @NotNull(message = "Password is required!")
    @Size(min = 8, max = 45, message = "Password is too short or too long!")
    private String password;

    @NotNull(message = "Email is required!")
    @Email(message = "Not an email!")
    private String email;

    public RegisterUserRequest(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
    }

    public RegisterUserRequest(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
