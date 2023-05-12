package com.pragma.api.repository;

import com.pragma.api.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla PROGRAM.
 */
@Repository
public interface IProgramRepository extends JpaRepository<Program, String> {
    Program findByProgramId(String name);
}
