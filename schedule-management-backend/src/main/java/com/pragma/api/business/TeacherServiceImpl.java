package com.pragma.api.business;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceDTO;
import com.pragma.api.domain.TeacherDTO;
import com.pragma.api.exception.ScheduleBadRequestException;
import com.pragma.api.model.Resource;
import com.pragma.api.model.Teacher;
import com.pragma.api.repository.ITeacherRepository;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherRepository iTeacherRepository;

    private final ModelMapper modelMapper;

    @Autowired
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
