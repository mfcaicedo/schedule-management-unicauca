package com.pragma.api.domain;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private LocalTime startingTime;
    //@NotNull
    private Integer courseId;
    @NotNull
    private Integer environmentId;
    //@NotNull
    private Integer eventId;
}
