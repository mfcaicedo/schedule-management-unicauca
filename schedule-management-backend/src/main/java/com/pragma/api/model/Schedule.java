package com.pragma.api.model;

import com.pragma.api.model.enums.DaysEnumeration;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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
    ////////////////////////////
    //se añadio esta parte 
    @Column(name = "starting_Date")
    private Date startingDate;

    @Column(name = "ending_Date")
    private Date endingDate;
    
    @Column(name ="is_reserve")
    @ColumnDefault("false")
    private boolean isReserve;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;
    //
    ////////////////////////////
    @ManyToOne
    @JoinColumn(name = "course_id",nullable = true)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "environment_id")
    private Environment environment;

}
