package com.pragma.api.repository;

import com.pragma.api.domain.CourseDTO;
import com.pragma.api.model.Course;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.model.Program;
import com.pragma.api.model.Resource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla COURSE.
 */
@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findAllBySubject_ProgramAndSubject_Semester(Program program, Integer semester, Pageable pageable);

    Page<Course> findAllBySubject_ProgramAndSubject_SemesterAndRemainingHoursGreaterThan(Program program,
            Integer semester, Integer remainingHours, Pageable pageable);

    @Repository
    public interface ICoursePersonRepository extends JpaRepository<CourseTeacher, Integer> {

        @Query(value = "SELECT * FROM course_person c WHERE c.personCode = :personCode", nativeQuery = true)
        List<CourseTeacher> findAllByTeacherCode(@Param("personCode") String personCode);

    }

    List<Course> findAllBySubject_Program_ProgramId(String programId);

}
