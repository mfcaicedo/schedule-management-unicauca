package com.pragma.api.repository;

import com.pragma.api.model.Program;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla PROGRAM.
 */
@Repository
public interface IProgramRepository extends JpaRepository<Program, String> {
    Program findByProgramId(String name);

    @Query(value = "SELECT * FROM program p WHERE p.department_id = :department_id",nativeQuery = true)
    List<Program> findByDepartmentId(@Param("department_id") Integer department_id);
    
    
}
