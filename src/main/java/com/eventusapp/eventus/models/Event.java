package com.eventusapp.eventus.models;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int event_id;

    @Column(name="user_id", nullable=false, unique=false)
    private int user_id;

    @Column(name="title", length=50, nullable=false, unique=false)
    private String title;

    @Column(name="description", length=500, nullable=false, unique=false)
    private String description;

    @Column(name="location", length=100, nullable=false, unique=false)
    private String location;

    @Column(name="startdate", nullable=false, unique=false)
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Column(name="enddate", nullable=false, unique=false)
    @Temporal(TemporalType.DATE)
    private Date end_date;

    @Column(name="starttime", length=8, nullable=false, unique=false)
    private LocalTime start_time;

    @Column(name="endtime", length=8, nullable=false, unique=false)
    private LocalTime end_time;

    @Column(name="photo", length=45, nullable=true, unique=false)
    private String photo;

    @Column(name="status", length=10, nullable=false, unique=false)
    private String status;

    @Column(name="created_at", nullable=false, unique=false)
    @Temporal(TemporalType.DATE)
    private Date created_at;



    public Event() {
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

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }




}
