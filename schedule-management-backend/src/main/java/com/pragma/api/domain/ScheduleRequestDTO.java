package com.pragma.api.domain;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.sql.Date;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ScheduleRequestDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;

    @NotNull
    private Date endingDate;
    @NotNull
    private LocalTime endingTime;
    @NotNull
    private boolean isReserv;
    @NotNull
    private Date startinDate;
    @NotNull
    private Date startingDate;
    @NotNull
    private LocalTime startingTime;
    @Nullable
    private Integer courseId;
    @NotNull
    private Integer environmentId;
    @Nullable
    private Integer eventId;
}
