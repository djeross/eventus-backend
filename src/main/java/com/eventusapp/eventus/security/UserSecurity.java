package com.eventusapp.eventus.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserSecurity {

    public PasswordEncoder  encoder() {
        return new BCryptPasswordEncoder();
    }
}
