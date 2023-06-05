package com.pragma.api.services;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.ScheduleRequestDTO;
import com.pragma.api.domain.ScheduleResponseDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.util.exception.ScheduleIntegrityException;

//import antlr.debug.Event;

import com.pragma.api.model.Course;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.Event;
import com.pragma.api.repository.ICourseRepository;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IEventRepository;
import com.pragma.api.repository.IScheduleRepository;
import com.pragma.api.repository.IPersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    private final ModelMapper modelMapper;
    private final IEnvironmentRepository environmentRepository;

    private final IScheduleRepository scheduleRepository;

    private final ICourseRepository courseRepository;

    private final IPersonRepository personRepository;
    // reporsitorio de eventos // parte nueva
    private final IEventRepository eventRepository;

    public ScheduleServiceImpl(ModelMapper modelMapper, IEnvironmentRepository environmentRepository,
            IScheduleRepository scheduleRepository, ICourseRepository courseRepository,
            IPersonRepository personRepository, IEventRepository eventRepository) {
        this.modelMapper = modelMapper;
        this.environmentRepository = environmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.personRepository = personRepository;
        // nuevo
        this.eventRepository = eventRepository;
    }

    @Override
    public ScheduleResponseDTO saveSchedule(final ScheduleRequestDTO saveRequest) {
        Optional<Course> courseOptRequest = this.courseRepository.findById(saveRequest.getCourseId());
        if (courseOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", saveRequest.getCourseId().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository
                .findById(saveRequest.getEnvironmentId());
        // request de event
        if (environmentOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.environment.id",
                    saveRequest.getEnvironmentId().toString());
        Optional<Event> eventOptRequest = null;
        if (saveRequest.isReserv()) {
            eventOptRequest = this.eventRepository.findById(saveRequest.getEventId());
        }
        if (eventOptRequest == null && saveRequest.isReserv())
            throw new ScheduleBadRequestException("bad.request.event.id", saveRequest.getEventId().toString());
        Course courseDb = courseOptRequest.get();
        if (this.scheduleRepository.existsByCourseAndDay(courseDb, saveRequest.getDay()))
            throw new ScheduleBadRequestException("bad.request.schedule.course.day", saveRequest.getDay().toString());
        if (this.scheduleRepository.existsByStartingTimeAndEndingTimeAndDayAndEnvironment(saveRequest.getStartingTime(),
                saveRequest.getEndingTime(), saveRequest.getDay(), environmentOptRequest.get()))
            throw new ScheduleBadRequestException("bad.request.schedule.course.day.time.environment",
                    environmentOptRequest.get().getName());
        int differenceHours = (int) getDifferenceHours(saveRequest.getStartingTime(), saveRequest.getEndingTime());
        // Se calcula la diferencia de horas no sobrepase las establecida que la
        // diferencia no sea negativa y que no sean menor a 1 los bloques
        if (differenceHours > courseDb.getRemainingHours() || differenceHours < 2)
            throw new ScheduleBadRequestException("bad.request.schedule.hours", courseDb.getId().toString());
        Schedule requestSchedule = Schedule
                .builder()
                .startingTime(saveRequest.getStartingTime())
                .endingTime(saveRequest.getEndingTime())
                .day(saveRequest.getDay())
                .build();
        requestSchedule.setEnvironment(environmentOptRequest.get());
        requestSchedule.setCourse(courseDb);
        courseDb.setRemainingHours((courseDb.getRemainingHours() - differenceHours));
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
        if (scheduleOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());
        Optional<Course> courseOptRequest = this.courseRepository.findById(updateRequest.getCourseId());
        if (courseOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", updateRequest.getCourseId().toString());
        Course concreteCourse = courseOptRequest.get();
        if (this.scheduleRepository.existsByCourseAndDay(concreteCourse, updateRequest.getDay()))
            throw new ScheduleBadRequestException("bad.request.schedule.course.day", updateRequest.getDay().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository
                .findById(updateRequest.getEnvironmentId());
        if (environmentOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.environment.id",
                    updateRequest.getEnvironmentId().toString());
        int differenceHours = (int) getDifferenceHours(updateRequest.getStartingTime(), updateRequest.getEndingTime());
        int oldScheduleDifferenceHours = (int) getDifferenceHours(scheduleOptRequest.get().getStartingTime(),
                scheduleOptRequest.get().getEndingTime());
        if (concreteCourse.equals(scheduleOptRequest.get().getCourse())) {
            concreteCourse.setRemainingHours(concreteCourse.getRemainingHours() + oldScheduleDifferenceHours);
        }
        if (differenceHours > concreteCourse.getRemainingHours())
            throw new ScheduleBadRequestException("bad.request.schedule.hours",
                    courseOptRequest.get().getId().toString());
        if (!concreteCourse.equals(scheduleOptRequest.get().getCourse())) {
            Course oldCourse = scheduleOptRequest.get().getCourse();
            oldCourse.setRemainingHours(oldCourse.getRemainingHours() + oldScheduleDifferenceHours);
            this.courseRepository.save(scheduleOptRequest.get().getCourse());
        }
        Schedule scheduleDb = scheduleOptRequest.get();
        scheduleDb.setCourse(courseOptRequest.get());
        scheduleDb.setEnvironment(environmentOptRequest.get());
        scheduleDb.setStartingTime(updateRequest.getStartingTime());
        scheduleDb.setEndingTime(updateRequest.getEndingTime());
        scheduleDb.setDay(updateRequest.getDay());
        concreteCourse.setRemainingHours(concreteCourse.getRemainingHours() - differenceHours);
        this.courseRepository.save(concreteCourse);
        return modelMapper.map(this.scheduleRepository.save(scheduleDb), ScheduleResponseDTO.class);
    }

    @Override
    public Boolean deleteSchedule(Long code) {
        try {
            Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
            if (scheduleOptRequest.isPresent())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());
            Course courseDb = scheduleOptRequest.get().getCourse();
            int differenceHours = (int) getDifferenceHours(scheduleOptRequest.get().getStartingTime(),
                    scheduleOptRequest.get().getEndingTime());
            courseDb.setRemainingHours((courseDb.getRemainingHours() + differenceHours));
            this.scheduleRepository.deleteById(code);
            this.courseRepository.save(courseDb);
            return true;
        } catch (Exception e) {
            throw new ScheduleIntegrityException(e.getMessage(), "");
        }
    }

    @Override
    public List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId) {
        Optional<Environment> environmentRequest = this.environmentRepository.findById(environmentId);
        if (environmentRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.environment.id", environmentId.toString());
        List<Schedule> schedules = this.scheduleRepository.findAllByEnvironment(environmentRequest.get());
        return schedules.stream()
                .map(schedule -> {
                    ScheduleResponseDTO scheduleResponseDTO = this.modelMapper.map(schedule, ScheduleResponseDTO.class);
                    scheduleResponseDTO.setColor(schedule.getCourse().getSubject().getProgram().getColor());
                    // scheduleResponseDTO.setColor(schedule.getCourse().getPerson().getProgram().getColor());
                    scheduleResponseDTO.setColor(schedule.getCourse().getSubject().getProgram().getColor());
                    return scheduleResponseDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * Necesario que se plantee una solucion diferente debido a que la relacion con
     * profesor es de muchos a muchos.
     *
     * @Override
     *           public List<ScheduleResponseDTO> getAllByPerson(String personCode)
     *           {
     *           Optional<Person> personRequest =
     *           this.personRepository.findById(personCode);
     *           if(personRequest.isEmpty()) throw new
     *           ScheduleBadRequestException("bad.request.person.id", personCode);
     *           List<Schedule> schedules =
     *           this.scheduleRepository.findAllByCoursePerson(personRequest.get());
     *           return schedules.stream()
     *           .map(schedule -> {
     *           ScheduleResponseDTO scheduleResponseDTO =
     *           this.modelMapper.map(schedule, ScheduleResponseDTO.class);
     *           scheduleResponseDTO.setColor(schedule.getCourse().getPerson().getProgram().getColor());
     *           return scheduleResponseDTO;
     *           })
     *           .collect(Collectors.toList());
     *           }
     */
    @Override
    public ScheduleResponseDTO getScheduleById(Long code) {
        try {
            Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
            if (scheduleOptRequest.isPresent())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());

            return this.modelMapper.map(scheduleOptRequest.get(), ScheduleResponseDTO.class);

        } catch (Exception e) {
            throw new ScheduleIntegrityException(e.getMessage(), "");
        }
    }


    @Override
    public Response<List<ScheduleResponseDTO>> findAllByEnvironmentId(Integer environmentId) {
        //Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de ambiente
//        if(!this.scheduleRepository.existsBy()) throw  new ScheduleBadRequestException("bad.request.event.event_name","");
        //System.out.println("Esto ocurre en la implementacion de servicios"+environmentId);
        List<Schedule> schedules = this.scheduleRepository.findAllByEnvironmentId(environmentId);
        List<ScheduleResponseDTO> ScheduleDTOlist = modelMapper.map(schedules,new TypeToken<List<ScheduleResponseDTO>>() {}.getType());
        Response<List<ScheduleResponseDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of schedules Finded successfully");
        response.setDeveloperMessage("List of schedules Finded successfully");
        response.setMoreInfo("localhost:8081/api/schedule(toDO)");
        response.setErrorCode("");
        response.setData(ScheduleDTOlist);
        return response;
    }


/*    @Override
    public List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllByEnvironment'");
    }*/
}
