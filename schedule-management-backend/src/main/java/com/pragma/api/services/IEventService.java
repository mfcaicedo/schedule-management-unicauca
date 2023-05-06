package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.Response;

public interface IEventService {

    public Response<EventDTO> findByeventName(String eventName);
    public Response<List<EventDTO>> findByeventManagerName(String eventManagerName);
    public Response<EventDTO> save(EventDTO event);
}
