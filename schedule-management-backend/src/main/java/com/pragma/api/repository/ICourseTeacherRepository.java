package com.pragma.api.repository;

import com.pragma.api.model.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTeacherRepository extends JpaRepository<CourseTeacher, Integer> {
}
