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

    //Verificar si existe por lo menos un programa
    boolean existsBy();

    //consultamos los programas que existen, que esten aosciados al id de la facultad que nos entregan
    @Query(value = "SELECT p.program_id,p.name FROM program p INNER JOIN department d " +
    " ON p.department_id=d.department_id WHERE d.faculty_id= :faculty_id", nativeQuery = true)
    //Se realiza busqueda de facultad mediante id para traer los edificios    
    List<Object[]> findProgramDataByFacultyId(@Param("faculty_id") String facultyId);

    @Query(value = "SELECT * FROM program p WHERE p.department_id = :department_id",nativeQuery = true)
    List<Program> findByDepartmentId(@Param("department_id") String department_id);

}
