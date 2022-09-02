package com.eventusapp.eventus.models;

import com.eventusapp.eventus.security.UserSecurity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name="fullname", length=50, nullable=false, unique=false)
    private String full_name;

    @Column(name="email", length=50, nullable=false, unique=true)
    private String email;

    @Column(name="password", length=256, nullable=false, unique=false)
    private String password;

    @Column(name="profilephoto", length=45, nullable=true, unique=false)
    private String profile_photo;

    @Column(name="role", length=8, nullable=false, unique=false)
    private String role;

    @Column(name="created_at", nullable=false, unique=false)
    @Temporal(TemporalType.DATE)
    private Date created_at;

    public User()  {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = formatter.format(date);
        //Converting the String back to java.util.Date
        Date current_date = null;
        try {
            current_date = formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.created_at = current_date;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(){
        this.user_id = user_id;
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
        UserSecurity usersecurity = new UserSecurity();
        this.password = usersecurity.encoder().encode(password);
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
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

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", role='" + role + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
