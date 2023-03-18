package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pragma.api.model.Faculty;
import com.pragma.api.model.Resource;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import lombok.*;

import javax.persistence.*;
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

    private Set<ResourceDTO> availableResources;
}
