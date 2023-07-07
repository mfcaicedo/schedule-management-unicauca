package com.pragma.api.domain;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.model.enums.RecurrenceEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class EventToScheduleDTO {

    @NotNull
    //@Enumerated(EnumType.STRING)
    private DaysEnumeration day;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RecurrenceEnumeration recurrence;
    
    private Date endingDate;
    @NotNull
    private LocalTime endingTime;
    @NotNull
    private Date startingDate;
    @NotNull
    private LocalTime startingTime;
    @NotNull
    private Integer environmentId;
    @NotNull
    private Integer weeks;
    
    
}

