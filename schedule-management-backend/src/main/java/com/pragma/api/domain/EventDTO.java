package com.pragma.api.domain;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.pragma.api.model.enums.EventTypeEnumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO  {
    
    private Integer event_id;
    private String eventName;
    private String eventManagerName;
    private String description;
    private ProgramDTO program;
    private PersonDTO person;
    @Enumerated(EnumType.STRING)
    private EventTypeEnumeration eventType;
    
}
