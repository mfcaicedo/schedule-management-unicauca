package com.pragma.api.domain;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.pragma.api.model.enums.PersonTypeEnumeration;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personCode;
    @NotBlank
    @NonNull
    private String fullName;
    @NotBlank
    @NonNull
    private DepartmentDTO department;

    @Enumerated(EnumType.STRING)
    private PersonTypeEnumeration personType;



}
