package com.pragma.api.repository;

import com.pragma.api.model.Course;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.Teacher;
import com.pragma.api.model.enums.DaysEnumeration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
    Boolean existsByStartingTimeAndEndingTimeAndDayAndEnvironment(LocalTime startingTime, LocalTime endingTime, DaysEnumeration day, Environment environment);
    Boolean existsByCourseAndDay(Course course, DaysEnumeration day);
    List<Schedule>findAllByEnvironment(Environment environment);
    List<Schedule>findAllByCourseTeacher(Teacher teacher);
}
