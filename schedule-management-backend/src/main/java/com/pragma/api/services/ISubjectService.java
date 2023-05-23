package com.pragma.api.services;


import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import com.pragma.api.model.Program;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Interface que permite definir las operaciones de negocio a realizar sobre la entidad Subject.
 */
public interface ISubjectService {

    /**
     * Metodo que permite crear un nuevo registro en base de datos, sobre la tabla SUBJECT, insertando la informacion de la materia que se
     * recibe mediante el DTO.
     *
     * @param subjectDTO
     *            {@link SubjectDTO} Objeto con la información a insertar, recibido en el cuerpo de la petición al servicio Rest
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    public Response<SubjectDTO> createSubject(SubjectDTO subjectDTO);

    /**
     * Método que permite consultar la información de una materia mediante su codigo
     *
     * @param code
     *            Codigo de la materia a consultar
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de la materia consultada
     */
    Response<SubjectDTO> getSubjectByCode(String code);

    /**
     * Método que permite consultar la información de todas las materias existentes
     *
     * @return {@link Response} Objeto de respuesta para el servicio, el cual contiene información sobre el resultado de la consulta,
     *         además de la información de las materias consultadas
     */
    Response<GenericPageableResponse> findAll(final Pageable pageable);

    /**
     * Servicio para obtener todas las materias de un programa
     * @param programId programa a consultar
     * @return lista de materias
     */
    List<SubjectDTO> findAllByProgram(Program programId);

    GenericPageableResponse findAllSubject(Pageable pageable);

    GenericPageableResponse findAllByProgramId(String program_id, Pageable pageable);

}
