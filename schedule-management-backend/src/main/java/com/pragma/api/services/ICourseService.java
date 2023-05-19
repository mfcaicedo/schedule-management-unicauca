package com.pragma.api.services;

import com.pragma.api.domain.CourseDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import org.springframework.data.domain.Pageable;

public interface ICourseService {
    /**
     * Metodo que permite crear un nuevo registro en base de datos, sobre la tabla COURSE, insertando la informacion del curso que se
     * recibe mediante el DTO.
     *
     * @param courseDTO
     *            {@link CourseDTO} Objeto con la información a insertar, recibido en el cuerpo de la petición al servicio Rest
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    public Response<CourseDTO> createCourse(CourseDTO courseDTO);

    /**
     * Método que permite consultar la información de un curso mediante su codigo
     *
     * @param code
     *            Codigo de la materia a consultar
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de la materia consultada
     */
    Response<SubjectDTO> getCourseByCode(String code);

    /**
     * Método que permite consultar la información de todos los cursos existentes
     *
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de las materias consultadas
     */
    Response<GenericPageableResponse> findAll(final Pageable pageable);
    GenericPageableResponse findAllBySubjectProgramAndSemester(final String programId, final Integer semester, final Pageable pageable);

    GenericPageableResponse findAllByAvailable(final String programId, final Integer semester, final Pageable pageable);


    CourseDTO findById(final Integer id);


}
