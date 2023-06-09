package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.FinalEventScheduleDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Person;

public interface IEventService {

    public Response<List<EventDTO>> findAllByeventName(String eventName);
    public Response<List<EventDTO>> findAllByeventManagerName(String eventManagerName);
    public Response<EventDTO> save(EventDTO event,Person personManager);
    public Response<EventDTO> saveEventToSchedule(FinalEventScheduleDTO finalEventScheduleDTO);
}
