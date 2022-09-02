package com.eventusapp.eventus.forms;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class EventForm  {



    @NotBlank(message = "Title is required!")
    private String title;
    @NotBlank(message = "Description is required!")
    private String description;
    @NotBlank(message = "Location is required!")
    private String location;
    @NotBlank(message = "Start Date is required!")
    private String start_date;
    @NotBlank(message = "End Date is required!")
    private String end_date;
    @NotBlank(message = "Start Time is required!")
    private String start_time;
    @NotBlank(message = "End Time is required!")
    private String end_time;

    private MultipartFile photo;


    public EventForm() {

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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }



}
