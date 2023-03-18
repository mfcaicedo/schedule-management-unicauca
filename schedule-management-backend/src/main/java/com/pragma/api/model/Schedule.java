package com.pragma.api.model;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;
    @Column(name = "starting_time")
    private LocalTime startingTime;
    @Column(name = "ending_time")
    private LocalTime endingTime;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    private Environment environment;

}
