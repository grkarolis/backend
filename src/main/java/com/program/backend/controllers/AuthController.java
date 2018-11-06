package com.program.backend.controllers;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.request.LoginUserRequest;
import com.program.backend.beans.request.RegisterUserRequest;
import com.program.backend.beans.response.AuthToken;
import com.program.backend.beans.response.RegisterUserResponse;
import com.program.backend.config.JwtTokenUtil;
import com.program.backend.services.AuthenticationService;
import com.program.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Component
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUser) throws AuthenticationException {
        System.out.println(loginUser);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = authenticationService.findOne(loginUser.getEmail());
        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    RegisterUserResponse register(@Valid @RequestBody RegisterUserRequest user) {
        User registeredUser = authenticationService.registerUser(user);
        return new RegisterUserResponse(registeredUser);
    }
}
