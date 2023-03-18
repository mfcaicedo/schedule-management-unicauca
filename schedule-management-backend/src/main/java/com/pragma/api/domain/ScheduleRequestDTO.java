package com.pragma.api.domain;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ScheduleRequestDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;
    @NotNull
    private LocalTime startingTime;
    @NotNull
    private LocalTime endingTime;
    @NotNull
    private Integer courseId;
    @NotNull
    private Integer environmentId;
}
