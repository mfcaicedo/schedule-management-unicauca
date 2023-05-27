package com.pragma.api.domain;

import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private String teacherCode;
    @NotBlank
    @NonNull
    private String fullName;
    @NotBlank
    @NonNull
    private DepartmentDTO department;

    private PersonTypeEnumeration personType;

}
