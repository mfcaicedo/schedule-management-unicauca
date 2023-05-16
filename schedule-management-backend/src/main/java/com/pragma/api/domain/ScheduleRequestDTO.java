package com.pragma.api.domain;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;

    private Date endingDate;

    private LocalTime endingTime;
    private boolean isReserv;

    private Date startingDate;

    private LocalTime startingTime;
    private Integer courseId;
    private Integer environmentId;
    private Integer eventId;
}
