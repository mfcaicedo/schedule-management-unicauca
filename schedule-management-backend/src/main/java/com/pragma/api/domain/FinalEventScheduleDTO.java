package com.pragma.api.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class FinalEventScheduleDTO {

    @NotNull
    private EventDTO event;
    @NotNull
    private List<EventToScheduleDTO> eventToScheduleList;
    
}
