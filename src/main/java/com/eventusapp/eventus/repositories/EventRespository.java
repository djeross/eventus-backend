package com.eventusapp.eventus.repositories;

import com.eventusapp.eventus.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EventRespository extends JpaRepository<Event,Integer> {

    @Query(
            value = "SELECT * FROM events WHERE status = 'PUBLISHED' ORDER BY startdate ASC ;",
            nativeQuery = true
    )
    List<Event> listAllPublishedEvents ();

    @Query(
            value = "SELECT * FROM events WHERE status = 'PENDING' ORDER BY created_at ASC;",
            nativeQuery = true
    )
    List<Event> listAllUnpublishedEvents ();

    @Query(
            value = "SELECT * FROM events WHERE user_id = :userid ;",
            nativeQuery = true
    )
    List<Event> listAllUserEvents ( @Param("userid") int userid);

    @Query(
            value = "DELETE FROM events WHERE user_id = :userid and event_id = :eventid ;",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void deleteUserEvent ( @Param("userid") int userid, @Param("eventid") int eventid);

    @Query(
            value = "UPDATE events SET status = 'PUBLISHED' WHERE event_id = :eventid ;",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void publishEvent ( @Param("eventid") int eventid);


    @Query(
            value = "SELECT * FROM events WHERE user_id = :userid and event_id = :eventid ;",
            nativeQuery = true
    )
    Event getUserEvent ( @Param("userid") int userid, @Param("eventid") int eventid);

    @Query(
            value = "SELECT * FROM events e WHERE e.title LIKE %:title%",
            nativeQuery = true
    )//JPQL QUERY use class name as table eg EVENT
    List <Event> searchEvent ( @Param("title") String title );
}
