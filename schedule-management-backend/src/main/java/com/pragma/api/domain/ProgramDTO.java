package com.pragma.api.domain;

import com.pragma.api.model.Department;
import com.pragma.api.model.Subject;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {

    private String programId;

    @NonNull
    @NotBlank
    private String name;
    @NonNull
    private DepartmentDTO department;


}
