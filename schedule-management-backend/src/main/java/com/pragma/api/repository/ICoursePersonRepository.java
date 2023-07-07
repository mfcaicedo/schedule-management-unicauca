package com.pragma.api.repository;

import com.pragma.api.domain.CourseDTO;
import com.pragma.api.domain.CoursePersonDTO;
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

/**
 * Repository de Spring para las operaciones CRUD sobre la tabla COURSE_PERSON.
 */
@Repository
public interface ICoursePersonRepository extends JpaRepository<CourseTeacher, Integer> {

    @Query(value = "SELECT * FROM course_person c WHERE c.personCode = :personCode", nativeQuery = true)
    List<CourseTeacher> findAllByTeacherCode(@Param("personCode") String personCode);

}
