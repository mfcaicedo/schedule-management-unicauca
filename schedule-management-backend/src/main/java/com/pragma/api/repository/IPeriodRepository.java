package com.pragma.api.repository;

import com.pragma.api.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Repository de Spring para las operaciones CRUD sobre la tabla PERIOD.
 */

public interface IPeriodRepository extends JpaRepository<Period, String> {

    @Query(value = "SELECT * FROM period WHERE state = 'IN_PROGRESS' LIMIT 1;",nativeQuery = true)
    Period GetActivePeriod();
}
