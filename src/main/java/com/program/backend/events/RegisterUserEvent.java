package com.program.backend.events.user;

import com.program.backend.beans.entity.User;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterUserEvent {

    @NonNull
    User user;
}
