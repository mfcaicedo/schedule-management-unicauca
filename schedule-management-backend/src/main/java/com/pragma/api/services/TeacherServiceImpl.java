package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.TeacherDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Teacher;
import com.pragma.api.repository.ITeacherRepository;
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
public class TeacherServiceImpl implements ITeacherService {

    private  ITeacherRepository iTeacherRepository;

    private  ModelMapper modelMapper;


    public TeacherServiceImpl(ITeacherRepository iTeacherRepository, ModelMapper modelMapper) {
        this.iTeacherRepository = iTeacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericPageableResponse findAllTeacher(Pageable pageable) {
        Page<Teacher> teacherPage = this.iTeacherRepository.findAll(pageable);
        if(teacherPage.isEmpty()) throw new ScheduleBadRequestException("bad.request.teacher.empty", "");
        return this.validatePageList(teacherPage);
    }


    private GenericPageableResponse validatePageList(Page<Teacher> teachersPage){
        List<TeacherDTO> resourcesDTOS = teachersPage.stream().map(x->modelMapper.map(x, TeacherDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(teachersPage, resourcesDTOS);
    }
}
