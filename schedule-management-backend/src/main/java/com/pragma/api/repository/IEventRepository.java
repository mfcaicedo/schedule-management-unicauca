package com.pragma.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pragma.api.model.Event;
public interface IEventRepository extends JpaRepository<Event,Integer> {

    @Query(value = "SELECT * FROM event u WHERE u.person_code = :person_code",
            nativeQuery = true)
    List<Event> findAllByPersonCode(@Param("person_code") String person_code);
    boolean existsEventByEventManagerName(String EventManagerName);
    List<Event> findAllByEventManagerName(String eventManagerName);


}

