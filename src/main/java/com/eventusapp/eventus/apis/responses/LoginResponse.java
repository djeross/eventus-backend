package com.eventusapp.eventus.apis.responses;

import com.eventusapp.eventus.models.User;

import java.util.Date;

public class LoginResponse {
    private int id;
    private String role;
    private Date created_at;



    private String token;

    public LoginResponse(User user, String token) {
        this.id= user.getUser_id();
        this.role = user.getRole();
        this.created_at = user.getCreated_at();
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
