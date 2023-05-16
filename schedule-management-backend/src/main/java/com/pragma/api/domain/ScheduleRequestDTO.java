package com.pragma.api.domain;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;

    @NotNull
    private Date endingDate;
    @NotNull
    private LocalTime endingTime;
    private boolean isReserv;
    @NotNull
    private Date startingDate;
    @NotNull
    private LocalTime startingTime;
    @NotNull
    private Integer courseId;
    @NotNull
    private Integer environmentId;
    @NotNull
    private Integer eventId;
}
