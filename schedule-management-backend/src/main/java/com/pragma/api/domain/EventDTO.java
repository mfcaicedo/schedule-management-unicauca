package com.pragma.api.domain;

import com.pragma.api.model.Department;
import com.pragma.api.model.Subject;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private int eventId;
    private String eventManagerName;
    private String eventName;
    private String description;
    private String programId;
    private String teacherId;
}
