package com.eventusapp.eventus.forms;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
public class LoginForm {

    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    public LoginForm() {
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


}
