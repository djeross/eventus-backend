package com.eventusapp.eventus.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@Service
public class DateTimeService {

    public Date fromatDate(String stringDate) {
        System.out.println(stringDate);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date current_date = null;
        try {
            current_date = formatter.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return current_date;
    }

    public LocalTime fromatTime(String stringTime) {
        LocalTime timeObject = LocalTime.parse(stringTime);
        return timeObject;
    }
}
