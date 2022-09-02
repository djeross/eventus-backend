package com.eventusapp.eventus.apis;

import com.eventusapp.eventus.annotations.AdminRequired;
import com.eventusapp.eventus.annotations.AuthorizationRequired;
import com.eventusapp.eventus.apis.responses.EventsResponse;
import com.eventusapp.eventus.forms.EventForm;
import com.eventusapp.eventus.models.Event;
import com.eventusapp.eventus.security.JWTService;
import com.eventusapp.eventus.services.EventService;
import com.eventusapp.eventus.services.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequestMapping(value = "/api/v1")
@RestController
@Validated
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    JWTService jwtService;

    @Autowired
    FileManagerService fileManagerService;

    @RequestMapping(value = "/event-test", method = RequestMethod.GET)
    public ResponseEntity<Object> eventTest(){
        return new ResponseEntity<>("Test From Event Controller", HttpStatus.OK);
    }


    /* ADD A NEW EVENT */
    @RequestMapping(value = "/events", method = {RequestMethod.POST}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    @AuthorizationRequired
    public ResponseEntity<Object> addEvent(
            @Valid @ModelAttribute EventForm form,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {

        String currentUserId= jwtService.getUserId(token); // implement jwt to jet id from payload
        Event createdEvent = eventService.addEvent(form, Integer.parseInt(currentUserId));
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    /*GET EVENTS*/
    @RequestMapping(value = "/events", method = {RequestMethod.GET})
    @AuthorizationRequired
    public ResponseEntity<Object> getAllEvents(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {

        List<Event> events = null;
        switch (jwtService.getRole(token)) {
            case "ADMIN":
                events = eventService.getAllEvents();
                break;
            case "REGULAR":
                events = eventService.getAllPublishedEvents();
                break;
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /*GET THE DETAILS OF AN EVENT*/
    @RequestMapping(value = "/events/{eid}", method = {RequestMethod.GET})
    @AuthorizationRequired
    public ResponseEntity<Object> getEventDetails(
            @PathVariable("eid") int eventid) {

        Event event  = eventService.getEventsDetails(eventid);
        Object o = (event==null) ? new EventsResponse("Event Not Found",false) :  event;
        return new ResponseEntity<>(o, HttpStatus.OK);
    }


    /*@RequestMapping(value = {"/events/{eid}/user/{uid}","/events/{eid}"}, method = {RequestMethod.PATCH})
    @AuthorizationRequired
    public ResponseEntity<Object> updateEvent(
            @PathVariable("eid") int eventid,
            @PathVariable("uid") Optional<Integer> userid,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {

        Boolean isSuccess= null;
        EventsResponse response= null;
        switch (jwtService.getRole(token)) {
            case "ADMIN":
                //response = eventService.adminDeleteEvent(eventid);
                break;
            case "REGULAR":
                //response = eventService.deleteUserEvent(userid.get(),eventid);
                break;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    @RequestMapping(value = {"/events/{eid}/user/{uid}","/events/{eid}"}, method = {RequestMethod.DELETE})
    @AuthorizationRequired
    public ResponseEntity<Object> deleteEvent(
            @PathVariable("eid") int eventid,
            @PathVariable("uid") Optional<Integer> userid,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {

        Boolean isSuccess= null;
        EventsResponse response= null;
        switch (jwtService.getRole(token)) {
            case "ADMIN":
                response = eventService.adminDeleteEvent(eventid);
                break;
            case "REGULAR":
                response = eventService.deleteUserEvent(userid.get(),eventid);
                break;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*ADMIN ENDPOINT USED TO PUBLISH EVENT*/
    @RequestMapping(value = "/events/publish/{eid}", method = {RequestMethod.PATCH})
    @AdminRequired
    public ResponseEntity<Object> Publish(
            @PathVariable("eid") int eventid) {

        EventsResponse eventsResponse;
        eventsResponse = eventService.publishEvent(eventid);
        return new ResponseEntity<>(eventsResponse, HttpStatus.OK);
    }


    /*SEARCHING FOR AN EVENT*/
    @RequestMapping(value = "/events/search", method = {RequestMethod.GET})
    @AuthorizationRequired
    public ResponseEntity<Object> AddEvent(
            @RequestParam String event_title) {

        List<Event> event =eventService.search(event_title);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @RequestMapping(value = "/uploads/{file}", method = {RequestMethod.GET}, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    //@AuthorizationRequired
    public ResponseEntity<Object> getfile(
            @PathVariable("file") String file) throws IOException {
            return new ResponseEntity<>(fileManagerService.load(file), HttpStatus.OK);
    }
}
