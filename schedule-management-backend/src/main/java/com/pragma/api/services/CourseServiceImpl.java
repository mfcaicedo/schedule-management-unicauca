package com.pragma.api.services;

import com.pragma.api.domain.CourseDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.model.Course;
import com.pragma.api.model.Program;
import com.pragma.api.repository.*;
import com.pragma.api.util.PageableUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final ModelMapper modelMapper;

    private final ICourseRepository iCourseRepository;

    private final IPersonRepository iPersonRepository;

    private final ISubjectRepository iSubjectRepository;

    private final IPeriodRepository iPeriodRepository;

    private final IProgramRepository programRepository;
    public CourseServiceImpl(ModelMapper modelMapper, ICourseRepository iCourseRepository, IPersonRepository iPersonRepository, ISubjectRepository iSubjectRepository, IPeriodRepository iPeriodRepository, IProgramRepository programRepository) {
        this.modelMapper = modelMapper;
        this.iCourseRepository = iCourseRepository;
        this.iPersonRepository = iPersonRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iPeriodRepository = iPeriodRepository;
        this.programRepository = programRepository;
    }

    @Override
    @Transactional
    public Response<CourseDTO> createCourse(CourseDTO courseDTO) {
        logger.debug("Init createCourse Business Course: {}", courseDTO.toString());
        Response<CourseDTO> response = new Response<>();

//        if(!iPersonRepository.existsById(courseDTO.getPersonCode())){
//            throw new ScheduleBadRequestException("bad.request.person.id", courseDTO.getPersonCode());
//        }

        if(!iSubjectRepository.existsById(courseDTO.getSubjectCode())){
            throw new ScheduleBadRequestException("bad.request.subject.id", courseDTO.getSubjectCode());
        }

        if(!iPeriodRepository.existsById(courseDTO.getPeriodId())){
            throw new ScheduleBadRequestException("bad.request.period.id", courseDTO.getPeriodId());
        }

        Course course = modelMapper.map(courseDTO,Course.class);
        course.setRemainingHours(iSubjectRepository.findById(courseDTO.getSubjectCode()).get().getWeeklyOverload());

        CourseDTO courseDTO1 = modelMapper.map(iCourseRepository.save(course),CourseDTO.class);

        response.setStatus(200);
        response.setUserMessage("Course created");
        response.setDeveloperMessage("Course created");
        response.setMoreInfo("localhost:8080/api/course");
        response.setErrorCode("");
        response.setData(courseDTO1);
        logger.debug("Finish createCourse Business");

        return response;
    }



    @Override
    public Response<SubjectDTO> getCourseByCode(String code) {
        return null;
    }

    @Override
    public Response<GenericPageableResponse> findAll(Pageable pageable) {
        logger.debug("Start findAllCourses Business");
        Page<Course> coursePage = this.iCourseRepository.findAll(pageable);
        if(coursePage.isEmpty()) throw new ScheduleBadRequestException("bad.request.course.empty", "");

        Response<GenericPageableResponse> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Courses found");
        response.setDeveloperMessage("Courses found");
        response.setMoreInfo("localhost:8080/api/course");
        response.setErrorCode("");
        response.setData(this.validatePageList(coursePage));
        logger.debug("Finish findAllCourses Business");
        return response;
    }

    @Override
    public GenericPageableResponse findAllBySubjectProgramAndSemester(String programId, Integer semester, Pageable pageable) {
        Program programDb = this.programRepository.findById(programId).orElseThrow(()->new ScheduleBadRequestException("bad.request.program.id", programId));
        Page<Course> coursePage = this.iCourseRepository.findAllBySubject_ProgramAndSubject_Semester(programDb, semester, pageable);
        return this.validatePageList(coursePage);
    }

    @Override
    public GenericPageableResponse findAllByAvailable(String programId, Integer semester, Pageable pageable) {
        Program programDb = this.programRepository.findById(programId).orElseThrow(()->new ScheduleBadRequestException("bad.request.program.id", programId));
        Page<Course> coursePage = this.iCourseRepository.findAllBySubject_ProgramAndSubject_SemesterAndRemainingHoursGreaterThan(programDb, semester,0, pageable);
        return this.validatePageList(coursePage);
    }

    @Override
    public CourseDTO findById(Integer id) {
        System.out.println("llego al servicio con el id: "+id);
        Optional<Course> course = this.iCourseRepository.findById(id);
        System.out.println("id curso: "+course.get().getId());
        System.out.println("id descripcion : "+course.get().getDescription());

        return modelMapper.map(this.iCourseRepository.findById(id), CourseDTO.class);
    }

    private GenericPageableResponse validatePageList(Page<Course> coursesPage){
        List<CourseDTO> coursesDTOS = coursesPage.stream().map(x->modelMapper.map(x, CourseDTO.class)).collect(Collectors.toList());
        return PageableUtils.createPageableResponse(coursesPage, coursesDTOS);
    }
}