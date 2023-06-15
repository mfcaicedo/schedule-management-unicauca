package com.pragma.api.repository;

import com.pragma.api.model.Department;

import com.pragma.api.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDeparmentRepository extends JpaRepository<Department,Integer> {
    Department findDepartmentByDepartmentName(String name);

    //Encontrar el id asociado al nombre de un determinado departamento
    //public String findDepartmentIdByDepartmentName(String DepartmentName);



}
