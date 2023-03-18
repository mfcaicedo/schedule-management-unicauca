package com.pragma.api.domain;

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

}
