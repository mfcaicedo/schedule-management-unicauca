package com.pragma.api.repository;
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
public interface IReportRepository extends JpaRepository<Schedule, Long> {

    boolean existsBy();
    //Query para traer todos los datos necesarios, de la tabla schedule, curso, programa, subject, environment
    //Por el id de environment
    @Query( value=" SELECT s.id,s.day,s.starting_time, s.ending_time,s.starting_Date,s.ending_Date, c.course_group, e.name as nombre_ambiente, "+
     " su.name as nombre_materia,p.name as nombre_programa,p.color FROM schedule s INNER JOIN environment e "+
     " ON e.environment_id= s.environment_id INNER JOIN course c ON c.course_id=s.course_id INNER JOIN "+
     " subject su ON su.subject_code= c.subject_code INNER JOIN program p ON p.program_id=su.program_id "+
     " WHERE s.environment_id= :environment_id", nativeQuery = true)

     List<Object[]> getCombinedDataScheduleByEnvironmentId(@Param("environment_id") Integer environment_id);

     
     //Metodo para traer los datos necesarios para realizar el reporte facultad/programa por programa id ordenado
     //ascendentemente por el campo semestre de materia
     @Query( value=" SELECT s.id,s.day,s.starting_time,s.ending_time,s.starting_Date,s.ending_Date, su.name as materiaName, "+
                   " su.semester, c.course_group,c.type_environment_required,pro.name as programaName,pro.color,e.name as ambienteName FROM SCHEDULE AS s "+
                   " INNER JOIN course AS c on s.course_id=c.course_id INNER JOIN subject AS su on "+
                   " su.subject_code=c.subject_code INNER JOIN environment AS e ON e.environment_id=s.environment_id"+
                   " INNER JOIN program AS pro on pro.program_id=su.program_id WHERE pro.program_id= :program_id"+
                   " ORDER BY su.semester ASC", nativeQuery = true)

    List<Object[]> getCombinedDataScheduleByProgramId(@Param("program_id") String program_id);


    //Metodo para traer los datos necesarios para realizar el reporte facultad/programa por programa id ordenado
    //ascendentemente por el campo semestre de materia
    @Query( value=" SELECT s.id,s.day,s.starting_time,s.ending_time,s.starting_Date,s.ending_Date, su.name as materiaName, "+
                  " su.semester, c.course_group,c.type_environment_required,pro.name as programaName,pro.color,e.name as ambienteName FROM SCHEDULE AS s "+
                  " INNER JOIN course AS c on s.course_id=c.course_id INNER JOIN subject AS su on "+
                  " su.subject_code=c.subject_code INNER JOIN environment AS e ON e.environment_id=s.environment_id"+
                  " INNER JOIN program AS pro on pro.program_id=su.program_id WHERE pro.program_id= :program_id"+
                  " AND su.semester= :semester", nativeQuery = true)

    List<Object[]> getCombinedDataScheduleByProgramIdSemester(@Param("program_id") String program_id,@Param("semester") Integer semester);


    //Metodo para traer los datos necesarios para realizar el reporte docente por personCode
    //ascendentemente por el campo semestre de materia
    @Query( value=" SELECT cp.course_teacher_id, p.full_name, su.program_id, s.day,s.starting_time,s.ending_time, su.name as materiaName, "+
                    "c.course_group, pro.name as programaName, pro.color, e.name as ambienteName FROM COURSE_TEACHER AS cp "+
                  "INNER JOIN course AS c on cp.course_id=c.course_id INNER JOIN subject AS su on "+
                  "su.subject_code=c.subject_code "+
                  "INNER JOIN schedule AS s on s.course_id = cp.course_id "+	
                  "INNER JOIN environment AS e ON e.environment_id = s.environment_id "+
                  "INNER JOIN person AS p on p.personCode = cp.personCode "+
                  "INNER JOIN program AS pro on pro.program_id=su.program_id WHERE cp.personCode= :personCode " , nativeQuery = true)

    List<Object[]> getCombinedDataCoursePersonByPersonCode(@Param("personCode") String personCode);

}