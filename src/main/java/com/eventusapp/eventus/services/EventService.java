package com.eventusapp.eventus.services;

import com.eventusapp.eventus.apis.responses.EventsResponse;
import com.eventusapp.eventus.forms.EventForm;
import com.eventusapp.eventus.models.Event;
import com.eventusapp.eventus.repositories.EventRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    FileManagerService fileservice;
    @Autowired
    DateTimeService dateTimeService;
    @Autowired
    EventRespository repository;

    /**
     *
     * @param form EventForm containing the details of an event
     * @param id ID of user creating event
     * @return Event
     */
    public Event addEvent (EventForm form,int id){
        Event event = new Event();
        event.setUser_id(id);
        event.setTitle(form.getTitle());
        event.setDescription(form.getDescription());
        event.setLocation(form.getLocation());
        event.setStart_date( dateTimeService.fromatDate( form.getStart_date()) );
        event.setEnd_date( dateTimeService.fromatDate(form.getEnd_date()) );
        event.setStart_time( dateTimeService.fromatTime(form.getStart_time()) );
        event.setEnd_time( dateTimeService.fromatTime(form.getEnd_time()) );
        //event.setStatus("PENDING");
        event.setStatus("PENDING");

        String generatedFileName = fileservice.saveFile(form.getPhoto());
        event.setPhoto(generatedFileName);

        Event newEvent;
        try {
            newEvent = repository.save(event);
        } catch(Exception e) {
            fileservice.removeFile(generatedFileName);
            throw e;
        }
        return newEvent;
    }




    /**
     * Return all events from persistent store.
     * @return  List<Event>
     */
    public List<Event> getAllEvents(){
        return repository.findAll();
    }

    /**
     * Get All events that have NOT  been published by an administrator. eg status = PENDING
     * @return  List<Event>
     */
    public List<Event> getAllUnpublishedEvents(){
        return repository.listAllUnpublishedEvents();
    }

    /**
     * Get All events that have been published by an administrator
     * @return  List<Event>
     */
    public List<Event> getAllPublishedEvents(){
        return repository.listAllPublishedEvents();
    }


    /**
     * Get All events crerated by a User;
     * @param userid ID of an existing user
     * @return  List<Event>
     */
    public List<Event> getAllUserEvents(int userid){
        return repository.listAllUserEvents(userid);
    }

    /**
     * Get the details of a single event;
     *
     * @param eventid ID of an event.
     * @return Optional<Event>
     */
    public Event getEventsDetails(int eventid){
        if (!(repository.findById(eventid).isPresent())){
            return null;
        }
        return repository.findById(eventid).get();
    }


    /**
     * Used to delete the events created by a regular user. Event must have the id of user present in order for delete to occur.
     * @param userid ID of user;
     * @param eventid ID of event to be deleted
     * @return DeleteEventResponse
     */
    public EventsResponse deleteUserEvent(int userid, int eventid){
        EventsResponse response = new EventsResponse();
        Event event= repository.getUserEvent(userid,eventid);
         if (event == null) {
             response.setMessage("Event to be Deleted not Found!");
             response.setSuccess(false);
             return response;
         }
        repository.deleteUserEvent(userid,eventid);
        fileservice.removeFile(event.getPhoto());
        response.setMessage("Event Deleted Successfully");
        response.setSuccess(true);
        return response;
    }

    /**
     * Used to delete events of users with a role ADMIN. ID not required
     * @param eventid ID of event to be deleted
     * @return DeleteEventResponse
     */
    public EventsResponse adminDeleteEvent(int eventid){
        EventsResponse response = new EventsResponse();
        Event event;
        if(!(repository.findById(eventid).isPresent())){
            response.setMessage("Event to be Deleted not Found!");
            response.setSuccess(false);
            return  response;
        }
        event = repository.findById(eventid).get();
        repository.deleteById(eventid);
        fileservice.removeFile(event.getPhoto());
        response.setMessage("Event Deleted Successfully");
        response.setSuccess(true);
        return response;
    }

    /**
     * Used to delete events of users with a role ADMIN. ID not required
     * @param eventid ID of event to be deleted
     * @return DeleteEventResponse
     */
    public EventsResponse publishEvent(int eventid){
        EventsResponse response = new EventsResponse();
        if(!(repository.findById(eventid).isPresent())){
            response.setMessage(String.format("Event with ID %x Not Present", eventid));
            response.setSuccess(false);
        }
        repository.publishEvent(eventid);
        response.setMessage("Event Published Successfully");
        response.setSuccess(true);
        return response;
    }

    /**
     * Search for event in event store.
     * @param title Tile of event
     * @return Event
     */
    public List<Event> search(String title) {
        System.out.println(title);
        List<Event> event = repository.searchEvent(title);
        return event;
    }


}
