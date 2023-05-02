package com.pragma.api.repository;

import com.pragma.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla EVENT.
 */
@Repository
public interface IEventRepository extends JpaRepository<Event, Integer> {
}
