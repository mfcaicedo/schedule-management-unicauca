package com.pragma.api.domain;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CourseDTO {

    /**
     * Id del curso
     */
    private Integer courseId;

    /**
     * Grupo del curso
     */
    @NotBlank(message = "El grupo no puede ser vacio")
    private String courseGroup;

    /**
     * Capacidad del curso
     */
    @NotNull(message = "La capacidad no puede ser vacio")
    private Integer courseCapacity;

    /**
     * Periodo del curso
     */
    @NotNull(message = "El periodo no puede ser vacio")
    private String periodId;

    /**
     * Codigo materia del curso
     */
    @NotNull(message = "El codigo de la materia no puede ser vacio")
    private String subjectCode;

    /**
     * Codigo profesor del curso
     */
    // @NotNull(message = "El codigo del profesor no puede ser vacio")
    // private String personCode;
    private Set<PersonDTO> profesoresAsignados;

    @NotNull(message = "")
    private Integer remainingHours;
}