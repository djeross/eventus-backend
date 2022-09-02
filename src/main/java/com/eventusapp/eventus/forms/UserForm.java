package com.eventusapp.eventus.forms;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Component
public class UserForm implements Serializable {

    @NotBlank(message = "Name is required!")
    private String full_name;

    @Email
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    private MultipartFile profile_photo;

    public UserForm() {

    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(MultipartFile profile_photo) {
        this.profile_photo = profile_photo;
    }




}

