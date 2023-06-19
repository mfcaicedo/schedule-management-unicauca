package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.api.model.Faculty;
import com.pragma.api.model.Person;
import com.pragma.api.model.Program;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String departmentId;
    private String departmentName;
    private Faculty faculty;
//    private Set<Person> persons;
//    private PersonDTO person;
//    private Set<Program> programs;

}
