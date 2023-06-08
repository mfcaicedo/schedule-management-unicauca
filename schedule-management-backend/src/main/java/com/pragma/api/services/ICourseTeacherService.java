package com.pragma.api.services;

import com.pragma.api.domain.CourseTeacherDTO;
import com.pragma.api.domain.Response;

public interface ICourseTeacherService {
    /**
     * Método para guargar un registro de CourseTeacher (Tabla intermeda entre Course y Person, indica los
     * cursos que dicta cada profesor)
     *
     * @param courseTeacherDTO Objeto con la información del registro a guardar
     * @return Objeto con la información del registro guardado
     */
    Response<CourseTeacherDTO> save(CourseTeacherDTO courseTeacherDTO);
}
