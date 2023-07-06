package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.CourseTeacherDTO;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.ScheduleResponseDTO;
import com.pragma.api.model.Course;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;

public interface ICourseTeacherService {
    /**
     * Método para guargar un registro de CourseTeacher (Tabla intermeda entre Course y Person, indica los
     * cursos que dicta cada profesor)
     *
     * @param courseTeacherDTO Objeto con la información del registro a guardar
     * @return Objeto con la información del registro guardado
     */
    Response<CourseTeacherDTO> save(CourseTeacherDTO courseTeacherDTO);
    List<CourseTeacherDTO> findCourseTeacherByCourseId(Integer courseId);
    List<CourseTeacherDTO> findCourseTeacherByPersonId(String personId);

}
