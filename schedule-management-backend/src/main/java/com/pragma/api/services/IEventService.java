package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.Response;

public interface IEventService {

    public Response<List<EventDTO>> findAllByeventName(String eventName);
    public Response<List<EventDTO>> findAllByeventManagerName(String eventManagerName);
    public Response<EventDTO> save(EventDTO event);
    public Response<List<EventDTO>>findByEventId(Long id);
}
