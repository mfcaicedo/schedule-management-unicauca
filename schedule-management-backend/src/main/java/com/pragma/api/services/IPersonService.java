package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);

    /***
     * Servicio para obtener todos los profesores de la db
     * @return lista de profesores
     */
    List<PersonDTO> findAllPersonByTypeTeacher();
}
