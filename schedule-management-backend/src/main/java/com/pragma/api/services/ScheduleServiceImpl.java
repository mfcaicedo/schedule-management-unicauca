package com.pragma.api.services;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.CourseTeacherDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.ScheduleRequestDTO;
import com.pragma.api.domain.ScheduleResponseDTO;
import com.pragma.api.util.exception.ScheduleBadRequestException;
import com.pragma.api.util.exception.ScheduleIntegrityException;

//import antlr.debug.Event;

import com.pragma.api.model.Course;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.Event;
import com.pragma.api.model.Person;
import com.pragma.api.repository.ICourseRepository;
import com.pragma.api.repository.ICourseTeacherRepository;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IEventRepository;
import com.pragma.api.repository.IScheduleRepository;
import com.pragma.api.repository.IPersonRepository;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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

    private final ICourseTeacherService CourseTeacherService;

    private final IPersonRepository personRepository;
    // reporsitorio de eventos // parte nueva
    private final IEventRepository eventRepository;

    public ScheduleServiceImpl(ModelMapper modelMapper, IEnvironmentRepository environmentRepository,
            IScheduleRepository scheduleRepository, ICourseRepository courseRepository,
            IPersonRepository personRepository, IEventRepository eventRepository,
            ICourseTeacherService CourseTeacherService) {
        this.modelMapper = modelMapper;
        this.environmentRepository = environmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.personRepository = personRepository;
        // nuevo
        this.eventRepository = eventRepository;
        this.CourseTeacherService = CourseTeacherService;
    }

    @Override
    public ScheduleResponseDTO saveSchedule(final ScheduleRequestDTO saveRequest) {
        Optional<Course> courseOptRequest = this.courseRepository.findById(saveRequest.getCourseId());
        if (!courseOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", saveRequest.getCourseId().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository
                .findById(saveRequest.getEnvironmentId());
        // request de event
        if (!environmentOptRequest.isPresent())
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
        // Obtener la hora de inicio y fin del nuevo curso
        LocalTime newCourseStartingTime = saveRequest.getStartingTime();

        // Verificar que la segunda hora del nuevo curso esté disponible
        LocalTime newCourseSecondHour = newCourseStartingTime.plusHours(1);
        if (this.scheduleRepository.existsByStartingTimeAndDayAndEnvironment(newCourseSecondHour, saveRequest.getDay(),
                environmentOptRequest.get())) {
            throw new ScheduleBadRequestException("bad.request.schedule.course.day.time",
                    environmentOptRequest.get().getName());
        }

        // verifica que el curso sea en el ambiente ideal
        if (!courseOptRequest.get().getTypeEnvironmentRequired()
                .equals(environmentOptRequest.get().getEnvironmentType().toString())) {
            throw new ScheduleBadRequestException("bad.request.schedule.course.environment",
                    environmentOptRequest.get().getName());

        }
        // verificar que los profesores del curso seleccionado no esten ocupados en la
        // franja horaria
         if (checkSchedulesToPersons(saveRequest)) {
            throw new ScheduleBadRequestException("bad.request.schedule.courseTeacher.person",environmentOptRequest.get().getName());

        }

        int differenceHours = (int) getDifferenceHours(saveRequest.getStartingTime(), saveRequest.getEndingTime());
        // Se calcula la diferencia de horas no sobrepase las establecida que la
        // diferencia no sea negativa y que no sean menor a 1 los bloques
        if (differenceHours > courseDb.getRemainingHours() || differenceHours < 2)
            throw new ScheduleBadRequestException("bad.request.schedule.hours", courseDb.getId().toString());
        Schedule requestSchedule = Schedule
                .builder()
                .startingDate(saveRequest.getStartinDate())
                .endingDate(saveRequest.getEndingDate())
                .startingTime(saveRequest.getStartingTime())
                .endingTime(saveRequest.getEndingTime())
                .isReserve(saveRequest.isReserv())
                .day(saveRequest.getDay())
                .build();
        requestSchedule.setEnvironment(environmentOptRequest.get());
        requestSchedule.setCourse(courseDb);
        courseDb.setRemainingHours((courseDb.getRemainingHours() - differenceHours));
        this.courseRepository.save(courseDb);
        if (saveRequest.isReserv()) {
            requestSchedule.setEvent(eventOptRequest.get());
        }

        return modelMapper.map(this.scheduleRepository.save(requestSchedule), ScheduleResponseDTO.class);
    }

    private long getDifferenceHours(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours();
    }

    @Override
    public ScheduleResponseDTO updateSchedule(final Long code, final ScheduleRequestDTO updateRequest) {
        Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
        if (!scheduleOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());

        if (scheduleOptRequest.get().isReserve() == true && scheduleOptRequest.get().getCourse() == null) {
            // throw new
            // ScheduleBadRequestException("bad.request.course.id",updateRequest.getCourseId().toString());
            if (updateRequest.getCourseId() != null && updateRequest.isReserv() == false) {
                Optional<Course> courseOptRequest = this.courseRepository.findById(updateRequest.getCourseId());
                if (courseOptRequest.isEmpty())
                    throw new ScheduleBadRequestException("bad.request.course.id",
                            updateRequest.getCourseId().toString());
                Optional<Environment> environmentOptRequest = this.environmentRepository
                        .findById(updateRequest.getEnvironmentId());
                if (environmentOptRequest.isEmpty())
                    throw new ScheduleBadRequestException("bad.request.environment.id",
                            updateRequest.getEnvironmentId().toString());
                Course courseDb = courseOptRequest.get();
                if (this.scheduleRepository.existsByCourseAndDay(courseDb, updateRequest.getDay()))
                    throw new ScheduleBadRequestException("bad.request.schedule.course.day",
                            updateRequest.getDay().toString());
                if (this.scheduleRepository.existsByStartingTimeAndEndingTimeAndDayAndEnvironment(
                        updateRequest.getStartingTime(),
                        updateRequest.getEndingTime(), updateRequest.getDay(), environmentOptRequest.get()))
                    throw new ScheduleBadRequestException("bad.request.schedule.course.day.time.environment",
                            environmentOptRequest.get().getName());
                int differenceHours = (int) getDifferenceHours(updateRequest.getStartingTime(),
                        updateRequest.getEndingTime());
                // Se calcula la diferencia de horas no sobrepase las establecida que la
                // diferencia no sea negativa y que no sean menor a 1 los bloques
                if (differenceHours > courseDb.getRemainingHours() || differenceHours < 2)
                    throw new ScheduleBadRequestException("bad.request.schedule.hours", courseDb.getId().toString());
                Schedule scheduleDb = scheduleOptRequest.get();
                scheduleDb.setCourse(courseOptRequest.get());
                scheduleDb.setEnvironment(environmentOptRequest.get());
                scheduleDb.setEvent(null);
                scheduleDb.setEndingDate(updateRequest.getEndingDate());
                scheduleDb.setStartingDate(updateRequest.getStartinDate());
                scheduleDb.setStartingTime(updateRequest.getStartingTime());
                scheduleDb.setEndingTime(updateRequest.getEndingTime());
                scheduleDb.setReserve(updateRequest.isReserv());
                scheduleDb.setDay(updateRequest.getDay());
                courseDb.setRemainingHours(courseDb.getRemainingHours() - differenceHours);
                this.courseRepository.save(courseDb);

                return modelMapper.map(this.scheduleRepository.save(scheduleDb), ScheduleResponseDTO.class);
            }
        }
        Optional<Course> courseOptRequest = this.courseRepository.findById(updateRequest.getCourseId());
        if (!courseOptRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", updateRequest.getCourseId().toString());
        Course concreteCourse = courseOptRequest.get();
        if (this.scheduleRepository.existsByCourseAndDay(concreteCourse, updateRequest.getDay()))
            throw new ScheduleBadRequestException("bad.request.schedule.course.day", updateRequest.getDay().toString());
        Optional<Environment> environmentOptRequest = this.environmentRepository
                .findById(updateRequest.getEnvironmentId());
        if (!environmentOptRequest.isPresent())
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
        scheduleDb.setEndingDate(updateRequest.getEndingDate());
        scheduleDb.setStartingDate(updateRequest.getStartinDate());
        scheduleDb.setStartingTime(updateRequest.getStartingTime());
        scheduleDb.setEndingTime(updateRequest.getEndingTime());
        scheduleDb.setReserve(updateRequest.isReserv());
        scheduleDb.setDay(updateRequest.getDay());
        concreteCourse.setRemainingHours(concreteCourse.getRemainingHours() - differenceHours);
        this.courseRepository.save(concreteCourse);
        return modelMapper.map(this.scheduleRepository.save(scheduleDb), ScheduleResponseDTO.class);
    }

    @Override
    public Boolean deleteSchedule(Long code) {
        try {
            Optional<Schedule> scheduleOptRequest = this.scheduleRepository.findById(code);
            if (!scheduleOptRequest.isPresent())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());
            Course courseDb2 = null;
            if (!scheduleOptRequest.get().isReserve()) {
                Course courseDb = scheduleOptRequest.get().getCourse();
                int differenceHours = (int) getDifferenceHours(scheduleOptRequest.get().getStartingTime(),
                        scheduleOptRequest.get().getEndingTime());
                courseDb.setRemainingHours((courseDb.getRemainingHours() + differenceHours));
                courseDb2 = courseDb;
            }
            this.scheduleRepository.deleteById(code);
            if (!scheduleOptRequest.get().isReserve())
                this.courseRepository.save(courseDb2);
            return true;
        } catch (Exception e) {
            throw new ScheduleIntegrityException(e.getMessage(), "");
        }
    }

    @Override
    public List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId) {
        Optional<Environment> environmentRequest = this.environmentRepository.findById(environmentId);
        if (!environmentRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.environment.id", environmentId.toString());
        List<Schedule> schedules = this.scheduleRepository.findAllByEnvironment(environmentRequest.get());
        return schedules.stream()
                .map(schedule -> {
                    ScheduleResponseDTO scheduleResponseDTO = this.modelMapper.map(schedule, ScheduleResponseDTO.class);
                    if (scheduleResponseDTO.getCourse() != null) {
                        scheduleResponseDTO.setColor(schedule.getCourse().getSubject().getProgram().getColor());
                        // scheduleResponseDTO.setColor(schedule.getCourse().getPerson().getProgram().getColor());
                        scheduleResponseDTO.setColor(schedule.getCourse().getSubject().getProgram().getColor());
                    }
                    /*
                     * else{
                     * scheduleResponseDTO.setColor(schedule.getEvent().getProgram().getColor());
                     * //
                     * scheduleResponseDTO.setColor(schedule.getCourse().getPerson().getProgram().
                     * getColor());
                     * scheduleResponseDTO.setColor(schedule.getEvent().getProgram().getColor());
                     * }
                     */
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
     *           ScheduleResponseDTO scheduleRe-sponseDTO =
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
            if (!scheduleOptRequest.isPresent())
                throw new ScheduleBadRequestException("bad.request.schedule.id", code.toString());

            return this.modelMapper.map(scheduleOptRequest.get(), ScheduleResponseDTO.class);

        } catch (Exception e) {
            throw new ScheduleIntegrityException(e.getMessage(), "");
        }
    }

    @Override
    public Response<List<ScheduleResponseDTO>> findAllByEnvironmentId(Integer environmentId) {
        // Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de
        // ambiente
        // if(!this.scheduleRepository.existsBy()) throw new
        // ScheduleBadRequestException("bad.request.event.event_name","");
        // System.out.println("Esto ocurre en la implementacion de
        // servicios"+environmentId);
        List<Schedule> schedules = this.scheduleRepository.findAllByEnvironmentId(environmentId);
        List<ScheduleResponseDTO> ScheduleDTOlist = modelMapper.map(schedules,
                new TypeToken<List<ScheduleResponseDTO>>() {
                }.getType());
        Response<List<ScheduleResponseDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of schedules Finded successfully");
        response.setDeveloperMessage("List of schedules Finded successfully");
        response.setMoreInfo("localhost:8081/api/schedule(toDO)");
        response.setErrorCode("");
        response.setData(ScheduleDTOlist);
        return response;
    }

    /*
     * @Override
     * public List<ScheduleResponseDTO> getAllByEnvironment(Integer environmentId) {
     * // TODO Auto-generated method stub
     * throw new
     * UnsupportedOperationException("Unimplemented method 'getAllByEnvironment'");
     * }
     */
    public boolean checkSchedulesToPersons(ScheduleRequestDTO scheduleRequest) {
        List<CourseTeacherDTO> courseTeachersList = this.CourseTeacherService
                .findCourseTeacherByCourseId(scheduleRequest.getCourseId());
        for (CourseTeacherDTO courseTeacher : courseTeachersList) {
            List<CourseTeacherDTO> courseTeachersListAux = this.CourseTeacherService
                    .findCourseTeacherByPersonId(courseTeacher.getPerson().getPersonCode());
            for (CourseTeacherDTO courseTeacherAux : courseTeachersListAux) {
                if (this.scheduleRepository.existsByStartingTimeAndEndingTimeAndDayAndCourse(
                        scheduleRequest.getStartingTime(),
                        scheduleRequest.getEndingTime(), scheduleRequest.getDay(), courseTeacherAux.getCourse())) {
                    return true;
                }

                // Verificar que la segunda hora del nuevo curso esté disponible
                LocalTime newCourseSecondHour = scheduleRequest.getStartingTime().plusHours(1);
                if (this.scheduleRepository.existsByStartingTimeAndDayAndCourse(newCourseSecondHour,
                        scheduleRequest.getDay(),
                        courseTeacherAux.getCourse())){
                    
                    return true;
                    
                }
            }
        }
        return false;
    }

    @Override
    public List<ScheduleResponseDTO> findSchedulesByCourseId(Integer CourseId) {
        Optional<Course> courseRequest = this.courseRepository.findById(CourseId);
        if (!courseRequest.isPresent())
            throw new ScheduleBadRequestException("bad.request.course.id", CourseId.toString());
        List<Schedule> schedules = this.scheduleRepository.findAllByCourse(courseRequest.get());
        return schedules.stream()
                .map(schedule -> {
                    ScheduleResponseDTO scheduleResponseDTO = this.modelMapper.map(schedule, ScheduleResponseDTO.class);
                    return scheduleResponseDTO;
                })
                .collect(Collectors.toList());
    }

}
