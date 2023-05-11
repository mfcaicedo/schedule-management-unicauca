package com.pragma.api.repository;

import com.pragma.api.model.Department;
import com.pragma.api.model.Event;
import com.pragma.api.model.Faculty;
import com.pragma.api.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeparmentRepository extends JpaRepository<Department,Integer> {

    Department findDepartmentByDepartmentName(String name);

    //Page<Department> findDepartmentByDepartmentName(String name);
}
