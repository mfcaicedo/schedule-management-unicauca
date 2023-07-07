package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.api.model.Department;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.pragma.api.model.enums.PersonTypeEnumeration;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personCode;
    private String fullName;
   // @Getter(onMethod_= @JsonIgnore)
   // private Set<CourseTeacherDTO> assignedSubjects;
    private DepartmentDTO department;
    private ProgramDTO programHead;
    private DepartmentDTO departmentHead;
    private PersonTypeEnumeration personType;


}