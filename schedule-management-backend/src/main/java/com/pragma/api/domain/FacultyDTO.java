package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {
    private String facultyId;
    private String facultyName;
    @Getter(onMethod_= @JsonIgnore)
    private Set<DepartmentDTO> departments;
    @Getter(onMethod_= @JsonIgnore)
    private Set<EnvironmentDTO> environments;
}
