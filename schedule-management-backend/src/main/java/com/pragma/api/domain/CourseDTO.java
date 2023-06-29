package com.pragma.api.domain;

import java.util.Set;

import com.pragma.api.model.Subject;
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
    private Integer id;

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

    private Integer remainingHours;

    /**
     * Tipo de ambiente requerido
     */
    private String typeEnvironmentRequired;

    /**
     * Codigo materia del curso
     */
    @NotNull(message = "El codigo de la materia no puede ser vacio")
    private Subject subject;


}