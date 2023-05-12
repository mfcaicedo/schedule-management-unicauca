package com.pragma.api.domain;

import lombok.*;


import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private String departmentId;

    @NonNull
    @NotBlank
    private String departmentName;

}
