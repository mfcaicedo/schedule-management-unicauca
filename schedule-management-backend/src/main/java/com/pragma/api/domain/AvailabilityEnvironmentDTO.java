package com.pragma.api.domain;


import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.model.enums.RecurrenceEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailabilityEnvironmentDTO {
    private Date starting_date;
    @NotNull(message = "la hora inicial no puede estar en null")
    private LocalTime starting_time;
    @NotNull(message = "la hora final no puede estar en null")
    private LocalTime ending_time;
    @NotNull(message = "la recurrencia no puede ser null")
    private RecurrenceEnumeration recurrence;
    private DaysEnumeration day;
    private Integer weeks;
    
}
