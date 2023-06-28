package com.pragma.api.services;

import com.pragma.api.domain.CourseTeacherDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.repository.ICourseTeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTeacherServiceImpl implements ICourseTeacherService {

    private final ModelMapper modelMapper;
    private final ICourseTeacherRepository courseTeacherRepository;

    @Autowired
    public CourseTeacherServiceImpl(ModelMapper modelMapper, ICourseTeacherRepository courseTeacherRepository) {
        this.modelMapper = modelMapper;
        this.courseTeacherRepository = courseTeacherRepository;
    }

    @Override
    public Response<CourseTeacherDTO> save(CourseTeacherDTO courseTeacherDTO) {
        CourseTeacher courseTeacher = modelMapper.map(courseTeacherDTO, CourseTeacher.class);
        CourseTeacherDTO courseTeacherDTO1 = modelMapper.map(courseTeacherRepository.save(courseTeacher), CourseTeacherDTO.class);
        Response<CourseTeacherDTO> response = new Response<>();
        response.setData(courseTeacherDTO1);
        response.setStatus(200);
        response.setUserMessage("Curso asignado al profesor correctamente");

        return response;
    }
}
