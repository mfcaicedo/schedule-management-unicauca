package com.pragma.api.repository;

import com.pragma.api.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeparmentRepository extends JpaRepository<Department,Integer> {
    Department findDepartmentByDepartmentName(String name);
}
