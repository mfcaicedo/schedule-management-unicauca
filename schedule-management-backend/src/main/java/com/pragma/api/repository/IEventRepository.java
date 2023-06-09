package com.pragma.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pragma.api.model.Event;
public interface IEventRepository extends JpaRepository<Event,Integer> {

    @Query(value = "SELECT * FROM event u WHERE u.event_name = :event_name",
            nativeQuery = true)
    List<Event> findAllByEventName(@Param("event_name") String event_name);
    boolean existsEventByEventName(String eventName);
    boolean existsEventByEventManagerName(String EventManagerName);
    List<Event> findAllByEventManagerName(String eventManagerName);


}
