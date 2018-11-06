package com.program.backend.services;

import com.program.backend.beans.entity.User;
import com.program.backend.beans.request.RegisterUserRequest;
import com.program.backend.events.user.RegisterUserEvent;
import com.program.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(value = "userAuth")
public class AuthenticationService  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(RegisterUserRequest user) {
        User newUser = new User(user);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(newUser);
        applicationEventPublisher.publishEvent(new RegisterUserEvent(savedUser));

        return savedUser;
    }
}
