package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pragma.api.model.Course;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import java.util.Date;

import org.springframework.lang.Nullable;

import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDTO {
    private Long id;
    private DaysEnumeration day;
    private LocalTime endingTime;
    private Date endingDate;
    private boolean isReserv;
    private Date startingDate;
    private LocalTime startingTime;
    @Nullable
    private CourseDTO course;
    private String color;
    private EnvironmentDTO environment;
    @Nullable
    private EventDTO event;
}
