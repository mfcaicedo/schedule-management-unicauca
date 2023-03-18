package com.pragma.api.repository;

import com.pragma.api.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla PERIOD.
 */
@Repository
public interface IPeriodRepository extends JpaRepository<Period, String> {
}
