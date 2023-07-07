package com.pragma.api.domain;

import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnvironmentDTO {

    private Integer id;
    private String name;
    private String location;
    private Integer capacity;
    private EnvironmentTypeEnumeration environmentType;
    private String facultyId;
    private FacultyDTO facultyDTO;
    private Set<ResourceDTO> availableResources;

    //Constructor personalizado para poder realizar la consulta y cargar el tipo de ambiente
    public EnvironmentDTO(Integer id, String name, EnvironmentTypeEnumeration environmentType) {
        this.id = id;
        this.name = name;
        this.environmentType = environmentType;
    }
}
