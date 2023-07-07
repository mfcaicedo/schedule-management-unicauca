package com.pragma.api.domain;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pragma.api.model.enums.TeacherCategoryEnumeration;

@Data
@NoArgsConstructor
public class CoursePersonDTO {

    /**
     * Id del curso
     */
    @NotNull(message = "El id  no puede ser vacio")
    private Integer courseTeacherId;

    /**
     * Grupo del curso
     */
    //@NotBlank(message = "El grupo no puede ser vacio")
    private TeacherCategoryEnumeration teacherCategory;

    /**
     * Capacidad del curso
     */
    @NotNull(message = "El id del curso no puede ser vacio")
    private Integer courseId;

    /**
     * Periodo del curso
     */
    @NotNull(message = "El codigo del profesor no puede ser vacio")
    private String personCode;

}