package com.pragma.api.business;

import com.pragma.api.domain.ScheduleRequestDTO;
import com.pragma.api.domain.ScheduleResponseDTO;
import com.pragma.api.exception.ScheduleBadRequestException;
import com.pragma.api.exception.ScheduleIntegrityException;
import com.pragma.api.model.Course;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.Teacher;
import com.pragma.api.repository.ICourseRepository;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IScheduleRepository;
import com.pragma.api.repository.ITeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements IScheduleService{


    private final ModelMapper modelMapper;
    private final IEnvironmentRepository environmentRepository;

    private final IScheduleRepository scheduleRepository;

    private final ICourseRepository courseRepository;

    private final ITeacherRepository teacherRepository;

    public ScheduleServiceImpl(ModelMapper modelMapper, IEnvironmentRepository environmentRepository, IScheduleRepository scheduleRepository, ICourseRepository courseRepository, ITeacherRepository teacherRepository) {
        this.modelMapper = modelMapper;
        this.environmentRepository = environmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public ScheduleResponseDTO saveSchedule(final ScheduleRequestDTO saveRequest) {
        Optional<Course> courseOptRequest = this.courseRepository.findById(saveRequest.getCourseId());
        if(courseOptRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.course.id", saveRequest.getCourseId().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository.findById(saveRequest.getEnvironmentId());
        if(environmentOptRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.id", saveRequest.getEnvironmentId().toString());
        Course courseDb = courseOptRequest.get();
        if(this.scheduleRepository.existsByCourseAndDay(courseDb, saveRequest.getDay())) throw new ScheduleBadRequestException("bad.request.schedule.course.day", saveRequest.getDay().toString());
        if(this.scheduleRepository.existsByStartingTimeAndEndingTimeAndDayAndEnvironment(saveRequest.getStartingTime(), saveRequest.getEndingTime(),saveRequest.getDay(),environmentOptRequest.get())) throw new ScheduleBadRequestException("bad.request.schedule.course.day.time.environment", environmentOptRequest.get().getName());
        int differenceHours = (int) getDifferenceHours(saveRequest.getStartingTime(), saveRequest.getEndingTime());
        if(differenceHours>courseDb.getRemainingHours()) throw new ScheduleBadRequestException("bad.request.schedule.hours",courseDb.getId().toString());
        Schedule requestSchedule = Schedule
                .builder()
                .startingTime(saveRequest.getStartingTime())
                .endingTime(saveRequest.getEndingTime())
                .day(saveRequest.getDay())
                .build();
        requestSchedule.setEnvironment(environmentOptRequest.get());
        requestSchedule.setCourse(courseDb);
        courseDb.setRemainingHours((courseDb.getRemainingHours()-differenceHours));
        this.courseRepository.save(courseDb);
        return modelMapper.map(this.scheduleRepository.save(requestSchedule), ScheduleResponseDTO.class);
    }

    private long getDifferenceHours(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours();
    }

    @Override
    public ScheduleResponseDTO updateSchedule(final Long code, final ScheduleRequestDTO updateRequest) {
        Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
        if(scheduleOptRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());
        Optional<Course> courseOptRequest = this.courseRepository.findById(updateRequest.getCourseId());
        if(courseOptRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.course.id", updateRequest.getCourseId().toString());
        Course concreteCourse = courseOptRequest.get();
        if(this.scheduleRepository.existsByCourseAndDay(concreteCourse, updateRequest.getDay())) throw new ScheduleBadRequestException("bad.request.schedule.course.day", updateRequest.getDay().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository.findById(updateRequest.getEnvironmentId());
        if(environmentOptRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.id", updateRequest.getEnvironmentId().toString());
        int differenceHours = (int) getDifferenceHours(updateRequest.getStartingTime(), updateRequest.getEndingTime());
        int oldScheduleDifferenceHours = (int) getDifferenceHours(scheduleOptRequest.get().getStartingTime(),scheduleOptRequest.get().getEndingTime());
        if(concreteCourse.equals(scheduleOptRequest.get().getCourse())){
            concreteCourse.setRemainingHours(concreteCourse.getRemainingHours()+oldScheduleDifferenceHours);
        }
        if(differenceHours>concreteCourse.getRemainingHours()) throw new ScheduleBadRequestException("bad.request.schedule.hours",courseOptRequest.get().getId().toString());
        if (!concreteCourse.equals(scheduleOptRequest.get().getCourse())){
            Course oldCourse = scheduleOptRequest.get().getCourse();
            oldCourse.setRemainingHours(oldCourse.getRemainingHours()+oldScheduleDifferenceHours);
            this.courseRepository.save(scheduleOptRequest.get().getCourse());
        }
        Schedule scheduleDb = scheduleOptRequest.get();
        scheduleDb.setCourse(courseOptRequest.get());
        scheduleDb.setEnvironment(environmentOptRequest.get());
        scheduleDb.setStartingTime(updateRequest.getStartingTime());
        scheduleDb.setEndingTime(updateRequest.getEndingTime());
        scheduleDb.setDay(updateRequest.getDay());
        concreteCourse.setRemainingHours(concreteCourse.getRemainingHours()-differenceHours);
        this.courseRepository.save(concreteCourse);
        return modelMapper.map(this.scheduleRepository.save(scheduleDb), ScheduleResponseDTO.class);
    }

    @Override
    public Boolean deleteSchedule(Long code) {
        try {
            Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
            if (scheduleOptRequest.isEmpty())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());
            Course courseDb = scheduleOptRequest.get().getCourse();
            int differenceHours = (int) getDifferenceHours(scheduleOptRequest.get().getStartingTime(), scheduleOptRequest.get().getEndingTime());
            courseDb.setRemainingHours((courseDb.getRemainingHours()+differenceHours));
            this.scheduleRepository.deleteById(code);
            this.courseRepository.save(courseDb);
            return true;
        }catch (Exception e){
            throw new ScheduleIntegrityException(e.getMessage(),"");
        }
    }

    @Override
    public List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId) {
        Optional<Environment> environmentRequest = this.environmentRepository.findById(environmentId);
        if(environmentRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.environment.id", environmentId.toString());
        List<Schedule> schedules = this.scheduleRepository.findAllByEnvironment(environmentRequest.get());
        return schedules.stream()
                .map(schedule -> {
                    ScheduleResponseDTO scheduleResponseDTO = this.modelMapper.map(schedule, ScheduleResponseDTO.class);
                    scheduleResponseDTO.setColor(schedule.getCourse().getTeacher().getProgram().getColor());
                    return scheduleResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDTO> getAllByTeacher(String teacherCode) {
        Optional<Teacher> teacherRequest = this.teacherRepository.findById(teacherCode);
        if(teacherRequest.isEmpty()) throw new ScheduleBadRequestException("bad.request.teacher.id", teacherCode);
        List<Schedule> schedules = this.scheduleRepository.findAllByCourseTeacher(teacherRequest.get());
        return schedules.stream()
                .map(schedule -> {
                    ScheduleResponseDTO scheduleResponseDTO = this.modelMapper.map(schedule, ScheduleResponseDTO.class);
                    scheduleResponseDTO.setColor(schedule.getCourse().getTeacher().getProgram().getColor());
                    return scheduleResponseDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ScheduleResponseDTO getScheduleById(Long code){
        try {
            Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
            if (scheduleOptRequest.isEmpty())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());

            return this.modelMapper.map(scheduleOptRequest.get(), ScheduleResponseDTO.class);

        }catch (Exception e){
            throw new ScheduleIntegrityException(e.getMessage(),"");
        }
    }
}
