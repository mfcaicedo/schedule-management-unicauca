package com.pragma.api.repository;

import com.pragma.api.model.Course;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.enums.DaysEnumeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
    Boolean existsByStartingTimeAndEndingTimeAndDayAndEnvironment(LocalTime startingTime, LocalTime endingTime, DaysEnumeration day, Environment environment);
    Boolean existsByCourseAndDay(Course course, DaysEnumeration day);
    boolean existsByStartingTimeAndDayAndEnvironment(LocalTime startingTime, DaysEnumeration day, Environment environment);
    List<Schedule>findAllByEnvironment(Environment environment);
    
//    List<Schedule>findAllByCoursePerson(Person person);

    //boolean existsBy();
    //@Query(value = "SELECT * FROM schedule WHERE environment_id = :environment_id", nativeQuery = true)
    List<Schedule>findAllByEnvironmentId(@Param("environment_id")Integer enviroment_id);

    @Query(value = "SELECT * FROM schedule WHERE course_id = :course_id", nativeQuery = true)
    List<Schedule>findAllByCourseId(@Param("course_id")Integer course_id);
//    List<Schedule>findAllByCourseTeacher(Teacher teacher);
    
    @Query(value = "SELECT * FROM schedule WHERE event_id = :event_id", nativeQuery = true)
    List<Schedule>findAllByEventId(@Param("event_id")Integer event_id);

}
