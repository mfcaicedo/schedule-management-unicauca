package com.pragma.api.repository;

import com.pragma.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, String> {
}
