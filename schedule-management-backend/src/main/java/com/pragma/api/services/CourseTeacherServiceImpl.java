package com.pragma.api.services;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.CourseTeacherDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.ScheduleResponseDTO;
import com.pragma.api.model.Course;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Person;
import com.pragma.api.model.Schedule;
import com.pragma.api.repository.ICourseRepository;
import com.pragma.api.repository.ICourseTeacherRepository;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTeacherServiceImpl implements ICourseTeacherService {

    private final ModelMapper modelMapper;
    private final ICourseTeacherRepository courseTeacherRepository;
    private final ICourseRepository courseRepository;
     private final IPersonRepository personRepository;

    @Autowired
    public CourseTeacherServiceImpl(ModelMapper modelMapper, ICourseTeacherRepository courseTeacherRepository, ICourseRepository courseRepository,IPersonRepository personRepository) {
        this.modelMapper = modelMapper;
        this.courseTeacherRepository = courseTeacherRepository;
        this.courseRepository= courseRepository;
        this.personRepository =personRepository;
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
    @Override
    public List<CourseTeacherDTO> findCourseTeacherByCourseId(Integer courseId){
        Optional<Course> courseRequest = this.courseRepository.findById(courseId);
        if(!courseRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", courseId.toString());
        List<CourseTeacher> courseTeachersList = this.courseTeacherRepository.findAllByCourse(courseRequest.get());
        return courseTeachersList.stream()
                .map(courseTeacher -> {
                    CourseTeacherDTO CourseTeacherDTO = this.modelMapper.map(courseTeacher, CourseTeacherDTO.class);
                    return CourseTeacherDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<CourseTeacherDTO> findCourseTeacherByPersonId(String personId){
        Optional<Person> personRequest = this.personRepository.findById(personId);
        if(!personRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.person.id", personId.toString());
        List<CourseTeacher> courseTeachersList = this.courseTeacherRepository.findAllByPerson(personRequest.get());
        return courseTeachersList.stream()
                .map(courseTeacher -> {
                    CourseTeacherDTO CourseTeacherDTO = this.modelMapper.map(courseTeacher, CourseTeacherDTO.class);
                    return CourseTeacherDTO;
                })
                .collect(Collectors.toList());

    }
}