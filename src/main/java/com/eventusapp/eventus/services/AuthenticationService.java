package com.eventusapp.eventus.services;

import com.eventusapp.eventus.forms.LoginForm;
import com.eventusapp.eventus.models.User;
import com.eventusapp.eventus.repositories.UserRepository;
import com.eventusapp.eventus.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    @Autowired
    UserSecurity usersecurity;

    @Autowired
    UserRepository userRepository;

    public User Login(LoginForm form){
        User user = userRepository.findByEmail(form.getEmail());
        usersecurity.encoder().matches(form.getPassword(), user.getPassword());
        return user;
    }

}
