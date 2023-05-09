package com.pragma.api.repository;

import com.pragma.api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IFacultyRepository extends JpaRepository<Faculty, String> {
    //boolean findByFacultyNameEquals(String name);
    @Query(value = "SELECT fac.faculty_name FROM `faculty` as fac = :faculty_id", 
    nativeQuery = true)
    //Se realiza busqueda de facultad mediante id
    Faculty findByFacultyId(@Param("faculty_id") String faculty_id);
    //Verificar si existe la facultad antes de proceder a buscar
    boolean existsFacultyByFacultyId(String facultyId);
    Faculty findByFacultyIdIs(String name);
}
