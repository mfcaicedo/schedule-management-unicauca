package com.pragma.api.repository;

import com.pragma.api.model.Person;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, String> {
    public List<Person> findAllByPersonTypeIsAndAndDepartmentIs(String personType, String department);
    public List<Person> findAllByPersonType(PersonTypeEnumeration personType);
}