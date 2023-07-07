package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pragma.api.model.Course;
import com.pragma.api.model.Schedule;
import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import java.sql.Date;
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
    private LocalTime endingTime;
    private Date endingDate;
    private boolean isReserv;
    private Date startinDate;
    private LocalTime startingTime;
    private CourseDTO course;
    private String color;
    private EnvironmentDTO environment;
    private EventDTO event;
}

/*
Cambio del grupo 4. REVISAR--------------------------------------

package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDTO {
    */
/*
    private Long id;
    private DaysEnumeration day;
    private LocalTime endingTime;
    private Date endingDate;
    private boolean isReserv;
    private Date startinDate;
    private LocalTime startingTime;
    private CourseDTO course;
    private String color;
    private EnvironmentDTO environment;
    private EventDTO event;
    *//*

    private Long id;
    //@NotNull
    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;

    //@NotNull
    private Date endingDate;
    //@NotNull
    private LocalTime endingTime;
    //@NotNull
    private boolean isReserv;
    //@NotNull
    private Date startinDate;
    //@NotNull
    private LocalTime startingTime;
    //@NotNull
    private Integer courseId;
    //@NotNull
    private Integer environmentId;
    //@NotNull
    private Integer eventId;
}
*/
