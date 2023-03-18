package com.pragma.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Clase que define un DTO para la transmision de la informacion de los materias.
 */
@Data
@NoArgsConstructor
public class SubjectDTO {

    /** Id de la materia */
    @NotBlank(message = "El codigo no puede ser vacio")
    private String subjectCode;

    /** Nombre de la materia */
    @NotBlank(message = "El nombre no puede ser vacio")
    private String name;

    /** Carga semanal de la materia */
    @NotNull(message = "La carga semanal no puede ser vacio")
    private Integer weeklyOverload;

    /** Estado de bloque de la materia */
    @NotNull(message = "El bloque no puede ser vacio")
    private Boolean timeBlock;

    /** Semestre de la materia */
    @NotNull(message = "El semestre no puede ser vacio")
    private Integer semester;

    /** Id del programa de la materia */
    @NotBlank(message = "El id del programa no puede ser vacio")
    private String programId;
}
