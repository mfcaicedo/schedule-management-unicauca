package com.pragma.api.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Event;
import com.pragma.api.repository.IEventRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEventRepository eventRepository;

    @Override
    public Response<EventDTO> findByeventName(String eventName) {
        
        if(!this.eventRepository.existsEventByEventName(eventName)) throw  new ScheduleBadRequestException("bad.request.event.event_name","");

        Event event = this.eventRepository.findByEventName(eventName);
        EventDTO eventDTO1 = modelMapper.map(event,EventDTO.class);
        Response<EventDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Event Finded successfully");
        response.setDeveloperMessage("Event Finded successfully");
        response.setMoreInfo("localhost:8080/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventDTO1);
        return response;
    }

    @Override
    public Response<List<EventDTO>> findByeventManagerName(String eventManagerName) {
        
        if(!this.eventRepository.existsEventByEventManagerName(eventManagerName)) throw  new ScheduleBadRequestException("bad.request.event.event_name","");

        List<Event> event = this.eventRepository.findAllByEventManagerName(eventManagerName);
        List<EventDTO> eventDTOlist = modelMapper.map(event,new TypeToken<List<EventDTO>>() {}.getType());
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
    public Response<EventDTO> save(EventDTO event) {
        Event eventEntity=this.modelMapper.map(event, Event.class);
        Event objEventEntity= this.eventRepository.save(eventEntity);
        EventDTO eventDTO=this.modelMapper.map(objEventEntity, EventDTO.class);
        Response<EventDTO> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("Event created successfully");
        response.setDeveloperMessage("Event created successfully");
        response.setMoreInfo("localhost:808/api/event(toDO)");
        response.setErrorCode("");
        response.setData(eventDTO);
        return response;
        
    }

    
    
}
