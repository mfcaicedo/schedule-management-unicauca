package com.pragma.api.repository;

import com.pragma.api.model.Course;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Person;
import com.pragma.api.model.Schedule;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICourseTeacherRepository extends JpaRepository<CourseTeacher, Integer> {
    /*@Query(value = "SELECT * FROM environment u WHERE u.id NOT IN ("+
        "SELECT r.id \n"+
        "FROM schedule r \n"+
        "WHERE r.starting_Date = :starting_date \n"+
          "AND r.starting_time >= :starting_time \n"+
          "AND r.ending_time <= :ending_time"+
      ")",
    nativeQuery = true)
    @Query(value = "SELECT * FROM course_teacher WHERE course_id = :course_id",
    nativeQuery = true)*/
    List<CourseTeacher>findAllByCourse(Course course);
    List<CourseTeacher>findAllByPerson(Person person);
}
