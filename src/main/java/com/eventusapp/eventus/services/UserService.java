package com.eventusapp.eventus.services;

import com.eventusapp.eventus.forms.UserForm;
import com.eventusapp.eventus.models.User;
import com.eventusapp.eventus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.text.ParseException;

@Service
public class UserService {

    @Autowired
    FileManagerService fileservice;

    @Autowired
    UserRepository repository;

    public User addNewUser(UserForm form){
        User user = null;
        user = new User();
        user.setFull_name(form.getFull_name());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setRole("REGULAR");
        String generatedFileName = fileservice.saveFile(form.getProfile_photo());
        user.setProfile_photo(generatedFileName);

        System.out.println(user.toString());
        User newUser;
        try {
             newUser = repository.save(user);
        } catch(Exception e) {
            fileservice.removeFile(generatedFileName);
            throw e;
        }
        return newUser;
    }


}
