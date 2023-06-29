package com.pragma.api.domain;


import lombok.*;


import javax.validation.constraints.NotBlank;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {
    private String programId;
    private String name;
    private String color;
    private DepartmentDTO department;
    private PersonDTO person;

    public ProgramDTO(String programId,String name){
        this.programId=programId;
        this.name=name;
    }

}
