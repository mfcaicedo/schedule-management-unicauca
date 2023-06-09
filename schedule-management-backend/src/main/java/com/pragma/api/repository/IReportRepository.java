package com.pragma.api.repository;
import com.pragma.api.model.Report;
import com.pragma.api.model.Schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository de Spring para las operaciones CRUD.
 */
@Repository
public interface IReportRepository extends JpaRepository<Report, Long> {

    boolean existsBy();
    //Query para traer todos los datos necesarios, de la tabla schedule, curso, programa, subject, environment
    //Por el id de environment
    @Query( value=" SELECT s.id,s.day,s.starting_time,s.ending_time,s.starting_Date,s.ending_Date, "+
     " e.name as nombre_ambiente, su.name as nombre_materia,p.name as nombre_programa,p.color FROM "+
     " schedule s INNER JOIN environment e ON e.id= s.environment_id "+
     " INNER JOIN course c ON c.course_id=s.course_id INNER JOIN subject su ON "+
     " su.subject_code= c.subject_code INNER JOIN program p ON p.program_id=su.program_id "+
     " WHERE environment_id= :environment_id", nativeQuery = true)
     //List<Report> getCombinedDataScheduleByEnvironmentId(@Param("environment_id") Integer environment_id);

     List<Object[]> getCombinedDataScheduleByEnvironmentId(@Param("environment_id") Integer environment_id);

}