package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Person;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {
    GenericPageableResponse findAllPerson(Pageable pageable);

    PersonDTO findByCode(String code);

    /***
     * Servicio para obtener todos los profesores de la db
     * @return lista de profesores
     */
    List<Person> findAllPersonByTypeTeacher();

    public Response<GenericPageableResponse> findAllByPersonType(Pageable pageable, String personType);

    public Response<GenericPageableResponse> findAllByDepartmentName(Pageable pageable, String departmentName, String personType);

    public Response<List<PersonDTO>> findAllTeachersByDepartmentId(String department_id);

    public Response<List<PersonDTO>> findAllTeachersByName(String name);

    public Response<String> findNameByPersonCode(String personCode);
}
