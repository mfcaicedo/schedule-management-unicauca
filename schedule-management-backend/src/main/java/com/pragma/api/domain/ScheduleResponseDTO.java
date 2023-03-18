package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pragma.api.model.Course;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDTO {
    private Long id;
    private DaysEnumeration day;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private CourseDTO course;
    private String color;
    private EnvironmentDTO environment;
}
