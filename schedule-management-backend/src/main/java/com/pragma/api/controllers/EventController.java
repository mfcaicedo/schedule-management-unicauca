package com.pragma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.api.domain.EventDTO;
import com.pragma.api.domain.FinalEventScheduleDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.services.IEventService;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {

    @Autowired
    private IEventService eventService;

    

    @GetMapping("/consultByName/{name}")
    public Response<List<EventDTO>> consultEventByName(@PathVariable String name) {
        return this.eventService.findAllByeventName(name);
    }

    @GetMapping("/consultByManagerName/{name}")
    public Response<List<EventDTO>> consultByManagerName(@PathVariable String name) {
        return this.eventService.findAllByeventManagerName(name);
    }

    @PostMapping("/EventToSchedule")
    public Response<EventDTO> EventToSchedule(@Valid @RequestBody FinalEventScheduleDTO finalEventScheduleDTO) {
        return this.eventService.saveEventToSchedule(finalEventScheduleDTO);
    }


    
}
