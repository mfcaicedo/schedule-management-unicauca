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
    private String color;

    @NonNull
    @NotBlank
    private String name;
    @NonNull
    private DepartmentDTO department;

    public ProgramDTO(String programId,String name){
        this.programId=programId;
        this.name=name;
    }
    

}
