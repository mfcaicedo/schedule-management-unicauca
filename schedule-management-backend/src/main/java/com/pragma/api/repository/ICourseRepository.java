package com.pragma.api.repository;

import com.pragma.api.domain.CourseDTO;
import com.pragma.api.model.Course;
import com.pragma.api.model.Program;
import com.pragma.api.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla COURSE.
 */
@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findAllBySubject_ProgramAndSubject_Semester(Program program,Integer semester, Pageable pageable);

    Page<Course> findAllBySubject_ProgramAndSubject_SemesterAndRemainingHoursGreaterThan(Program program,Integer semester,Integer remainingHours, Pageable pageable);

    List<Course> findAllBySubject_Program_ProgramId(String programId);
}
