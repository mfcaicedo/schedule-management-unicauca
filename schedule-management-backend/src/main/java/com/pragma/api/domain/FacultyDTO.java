package com.pragma.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {
    private String facultyId;
    private String facultyName;
    //private Set<DepartmentDTO> departments;
    //private Set<EnvironmentDTO> environments;
}
