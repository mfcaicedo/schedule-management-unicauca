package com.pragma.api.repository;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.model.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDeparmentRepository extends JpaRepository<Department,Integer> {
    Department findDepartmentByDepartmentName(String name);

    @Query(value = "SELECT * FROM department d WHERE d.faculty_id = :faculty_id",nativeQuery = true)
    List<Department> findByFacultyId(@Param("faculty_id") String faculty_id);
    
}
