package com.pragma.api.services;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.model.Department;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Event;
import com.pragma.api.repository.IDeparmentRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Person;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.PageableUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PersonServiceImpl implements IPersonService {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentServiceImpl.class);

    private final IPersonRepository iPersonRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private IDeparmentRepository deparmentRepository;

    @Autowired
    public PersonServiceImpl(IPersonRepository iPersonRepository, ModelMapper modelMapper) {
        this.iPersonRepository = iPersonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericPageableResponse findAllPerson(Pageable pageable) {
        Page<Person> personPage = this.iPersonRepository.findAll(pageable);
        return this.validatePageList(personPage);
    }

    @Override
    public List<Person> findAllPersonByTypeTeacher() {
        List<Person> teachers = this.iPersonRepository.findAllByPersonType(PersonTypeEnumeration.TEACHER);
        return teachers;
    }
    @Override
    public PersonDTO findByCode(String code) {
        Optional<Person> person = this.iPersonRepository.findById(code);
        PersonDTO personDTO = new PersonDTO();
        personDTO = modelMapper.map(person,PersonDTO.class);
        return  personDTO;

    }
    @Override
    public Response<GenericPageableResponse> findAllByPersonType(Pageable pageable, String personType) {
        Page<Person> personPage = this.iPersonRepository.findAllByPersonType(pageable, personType);

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Teachers found");
        response.setDeveloperMessage("Teachers found");
        response.setErrorCode("");
        List<Person> resources = personPage.stream().map(x->modelMapper.map(x, Person.class)).collect(Collectors.toList());
        response.setData(PageableUtils.createPageableResponse(personPage, resources));

        return response;
    }

    @Override
    public Response<GenericPageableResponse> findAllByDepartmentName(Pageable pageable, String departmentId, String personType) {
        //buscar el id del departamento y se le envia el nombre
        PersonTypeEnumeration type = personType.equals("TEACHER") ? PersonTypeEnumeration.TEACHER : PersonTypeEnumeration.ADMINISTRATIVE;
        Page<Person> personPage = this.iPersonRepository.findAllByPersonTypeAndDepartmentDepartmentId(type,departmentId, pageable);
        personPage.forEach(x -> System.out.println(x.getDepartment().getDepartmentId()));
        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Departments found");
        response.setDeveloperMessage("Departments found");
        response.setErrorCode("");
        List<Person> resources = personPage.stream().map(x->modelMapper.map(x, Person.class)).collect(Collectors.toList());
        response.setData(PageableUtils.createPageableResponse(personPage, resources));
        return response;
    }

    private GenericPageableResponse validatePageList(Page<Person> personsPage){
        List<PersonDTO> resourcesDTOS = personsPage.stream().map(x->modelMapper.map(x, PersonDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(personsPage, resourcesDTOS);
    }

    @Override
    public Response<List<PersonDTO>> findAllTeachersByDepartmentId(String department_id) {

        List<Person> teachers = this.iPersonRepository.findAllTeachersByDepartmetId(department_id);
        List<PersonDTO> TeachersDTOlist = modelMapper.map(teachers,new TypeToken<List<PersonDTO>>() {}.getType());
        Response<List<PersonDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of teachers Finded successfully");
        response.setDeveloperMessage("List of teachers Finded successfully");
        response.setMoreInfo("localhost:8081/api/person(toDO)");
        response.setErrorCode("");
        response.setData(TeachersDTOlist);
        return response;
    }

    @Override
    public Response<List<PersonDTO>> findAllTeachersByName(String name) {

        List<Person> teachers = this.iPersonRepository.findAllTeachersByName(name);
        List<PersonDTO> TeachersDTOlist = modelMapper.map(teachers,new TypeToken<List<PersonDTO>>() {}.getType());
        Response<List<PersonDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of teachers Finded successfully");
        response.setDeveloperMessage("List of teachers Finded successfully");
        response.setMoreInfo("localhost:8081/api/person(toDO)");
        response.setErrorCode("");
        response.setData(TeachersDTOlist);
        return response;
    }

    @Override
    public Response<String> findNameByPersonCode(String personCode) {
        String teacher_name = this.iPersonRepository.findNameByCode(personCode);
        Response<String> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of teachers Finded successfully");
        response.setDeveloperMessage("List of teachers Finded successfully");
        response.setMoreInfo("localhost:8081/api/person(toDO)");
        response.setErrorCode("");
        response.setData(teacher_name);
        return response;
    }
}
