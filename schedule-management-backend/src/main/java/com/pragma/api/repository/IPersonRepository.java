package com.pragma.api.repository;

import com.pragma.api.model.Environment;
import com.pragma.api.model.Event;
import com.pragma.api.model.Person;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, String> {
    public List<Person> findAllByPersonTypeIsAndAndDepartmentIs(String personType, String department);
    public List<Person> findAllByPersonType(PersonTypeEnumeration personType);

    @Query(value = "SELECT * FROM person p WHERE p.person_type = :person_type",nativeQuery = true)
    Page<Person> findAllByPersonType(Pageable pageable, @Param("person_type") String person_type);

    @Query(value = "SELECT * FROM person p WHERE p.department.departmentId = :departmentId AND p.person_type = teacher",nativeQuery = true)
    Page<Person> findAllByDepartmetId(@Param("departmentId") String departmentId, Pageable pageable);

    Page<Person> findAllByPersonTypeAndDepartmentDepartmentId(String personType,String DepartmentId, Pageable pageable);

}

    //Page<Person> findAllByDepartmetId(String departmentId, Pageable pageable);

