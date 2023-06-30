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
    private FacultyDTO faculty;
    //@Getter(onMethod_= @JsonIgnore)
    //private Set<PersonDTO> persons;
    private PersonDTO person;
    //@Getter(onMethod_= @JsonIgnore)
    //private Set<ProgramDTO> programs;

}
