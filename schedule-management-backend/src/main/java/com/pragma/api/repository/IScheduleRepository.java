package com.pragma.api.repository;

import com.pragma.api.domain.ScheduleRequestDTO;
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
    List<Schedule>findAllByCourse(Course course);
    Boolean existsByStartingTimeAndEndingTimeAndDayAndCourse(LocalTime startingTime, LocalTime endingTime, DaysEnumeration day, Course course);
    boolean existsByStartingTimeAndDayAndCourse(LocalTime startingTime, DaysEnumeration day, Course course);
    
            //    List<Schedule>findAllByCoursePerson(Person person);

    //boolean existsBy();
    //@Query(value = "SELECT * FROM schedule WHERE environment_id = :environment_id", nativeQuery = true)
    List<Schedule>findAllByEnvironmentId(@Param("environment_id")Integer enviroment_id);
//    List<Schedule>findAllByCourseTeacher(Teacher teacher);
}
