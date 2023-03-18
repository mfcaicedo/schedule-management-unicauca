package com.pragma.api.repository;

import com.pragma.api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacultyRepository extends JpaRepository<Faculty, String> {
}
