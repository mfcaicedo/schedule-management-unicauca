package com.pragma.api.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.EventToScheduleDTO;
import com.pragma.api.domain.FinalEventScheduleDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Environment;
import com.pragma.api.model.Event;
import com.pragma.api.model.Period;
import com.pragma.api.model.Person;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.enums.RecurrenceEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IEventRepository;
import com.pragma.api.repository.IPeriodRepository;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.repository.IScheduleRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IEnvironmentRepository environtmentRepository;

    @Autowired
    private IPeriodRepository periodRepository;

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public Response<List<EventDTO>> findAllByeventName(String eventName) {

        if (!this.eventRepository.existsEventByEventName(eventName))
            throw new ScheduleBadRequestException("bad.request.event.event_name", "");

        List<Event> event = this.eventRepository.findAllByEventName(eventName);
        List<EventDTO> eventDTO1 = modelMapper.map(event, new TypeToken<List<EventDTO>>() {
        }.getType());
        Response<List<EventDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Event Finded successfully");
        response.setDeveloperMessage("Event Finded successfully");
        response.setMoreInfo("localhost:8080/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventDTO1);
        return response;
    }

    @Override
    public Response<List<EventDTO>> findAllByeventManagerName(String eventManagerName) {

        if (!this.eventRepository.existsEventByEventManagerName(eventManagerName))
            throw new ScheduleBadRequestException("bad.request.event.event_name", "");

        List<Event> event = this.eventRepository.findAllByEventManagerName(eventManagerName);
        List<EventDTO> eventDTOlist = modelMapper.map(event, new TypeToken<List<EventDTO>>() {
        }.getType());
        Response<List<EventDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of Events Finded successfully");
        response.setDeveloperMessage("List of Events Finded successfully");
        response.setMoreInfo("localhost:8080/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventDTOlist);
        return response;
    }

    @Override
    public Response<EventDTO> save(EventDTO event, Person personManager) {

        Response<EventDTO> response = new Response<>();
        Event eventEntity = this.modelMapper.map(event, Event.class);
        eventEntity.setPerson(personManager);
        Event objEventEntity = this.eventRepository.save(eventEntity);
        EventDTO eventDTO = this.modelMapper.map(objEventEntity, EventDTO.class);
        response.setStatus(200);
        response.setUserMessage("Event created successfully");
        response.setDeveloperMessage("Event created successfully");
        response.setMoreInfo("localhost:808/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventDTO);
        return response;
    }

    @Override
    public Response<EventDTO> saveEventToSchedule(FinalEventScheduleDTO finalEventScheduleDTO) {
        Person person = this.personRepository.findById(finalEventScheduleDTO.getEvent().getPerson()).orElse(null);
        Response<EventDTO> response = new Response<>();
        if (person == null) {
            response.setStatus(404);
            response.setUserMessage("Internal error the person not found");
            response.setDeveloperMessage("Internal error the person not found");
            response.setMoreInfo("localhost:8080/api/event(toDO)");
            response.setErrorCode("");
            response.setData(null);
            return response;
        }
        List<Environment> listEnviro = this.verificarListaEnviroment(finalEventScheduleDTO.getEventToScheduleList());
        if (listEnviro == null) {
            response.setStatus(404);
            response.setUserMessage("Internal error the environtment not found");
            response.setDeveloperMessage("Internal error the environtment not found");
            response.setMoreInfo("localhost:8080/api/event(toDO)");
            response.setErrorCode("");
            response.setData(null);
            return response;
        }
        // se crea el evento cuando ya se verifico que el profesor exista y los
        // ambientes tambien
        Response<EventDTO> eventdto = this.save(finalEventScheduleDTO.getEvent(), person);
        Event eventEntity = this.modelMapper.map(eventdto.getData(), Event.class);
        eventEntity.setPerson(person);
        int contador = 0;
        for (EventToScheduleDTO t : finalEventScheduleDTO.getEventToScheduleList()) {
            // Environment environtmentEntity =
            // this.environtmentRepository.findById(t.getEnvironmentId()).orElse(null);
            Date endingDate = null;
            if (t.getRecurrence().equals(RecurrenceEnumeration.DIA)) {
                endingDate = t.getStartingDate();

            } else if (t.getRecurrence().equals(RecurrenceEnumeration.SEMANA)) {
                LocalDate StartingDate = t.getStartingDate().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate NewDate = StartingDate.plusDays(7 * t.getWeeks());// multiplicar por la cantidad de la
                                                                            // semanas
                endingDate = Date.from(NewDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            } else if (t.getRecurrence().equals(RecurrenceEnumeration.SEMESTRE)) {
                Period actualPeriod = this.periodRepository.GetActivePeriod();
                t.setStartingDate(actualPeriod.getInitDate());
                endingDate = actualPeriod.getEndDate();

            }
            Schedule schedule = new Schedule(null, t.getDay(), t.getStartingTime(),
                    t.getEndingTime(), t.getStartingDate(), endingDate, true, eventEntity, null,
                    listEnviro.get(contador));
            this.scheduleRepository.save(schedule);
            contador++;

        }
        response.setStatus(200);
        response.setUserMessage("Event created in schedule successfully");
        response.setDeveloperMessage("Event created in schedule successfully");
        response.setMoreInfo("localhost:8080/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventdto.getData());
        return response;
    }

    private List<Environment> verificarListaEnviroment(List<EventToScheduleDTO> lista) {
        int varContador = 0;

        List<Environment> listaAmbientes = new ArrayList<>();
        for (EventToScheduleDTO t : lista) {
            Environment environtmentEntity = this.environtmentRepository.findById(t.getEnvironmentId()).orElse(null);
            if (environtmentEntity != null) {
                listaAmbientes.add(environtmentEntity);
                varContador++;
            }
        }

        if (varContador == lista.size()) {
            return listaAmbientes;
        }

        return null;

    }

}
