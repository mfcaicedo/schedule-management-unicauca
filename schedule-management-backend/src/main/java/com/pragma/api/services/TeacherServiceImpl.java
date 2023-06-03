package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
//import com.pragma.api.domain.PersonDTO;
import com.pragma.api.model.Person;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.util.PageableUtils;
import com.pragma.api.util.file.FileTeachers;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//public class TeacherServiceImpl implements IPersonService {
public class TeacherServiceImpl {

    private IPersonRepository iPersonRepository;

    private  ModelMapper modelMapper;


    public TeacherServiceImpl(IPersonRepository iPersonRepository, ModelMapper modelMapper) {
        this.iPersonRepository = iPersonRepository;
        this.modelMapper = modelMapper;
    }

    //todo se debe corregir
    /*@Override
    public GenericPageableResponse findAllTeacher(Pageable pageable) {
        Page<Person> teacherPage = this.iPersonRepository.findAll(pageable);
        if(teacherPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.teacher.empty", "");
        return this.validatePageList(teacherPage);
    }*/


    //todo se debe corregir
    /*private GenericPageableResponse validatePageList(Page<Person> teachersPage){
        List<PersonDTO> resourcesDTOS = teachersPage.stream().map(x->modelMapper.map(x, PersonDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(teachersPage, resourcesDTOS);
    }*/

    /*@Override
    public GenericPageableResponse findAllPerson(Pageable pageable) {
        return null;
    }*/
}
