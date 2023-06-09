package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);

    PersonDTO findByCode(String code);

    /***
     * Servicio para obtener todos los profesores de la db
     * @return lista de profesores
     */
    List<PersonDTO> findAllPersonByTypeTeacher();

    public Response<GenericPageableResponse> findAllByPersonType(Pageable pageable, String personType);

    public Response<GenericPageableResponse> findAllByDepartmentId(Pageable pageable, String departmentId, String personType);
}
