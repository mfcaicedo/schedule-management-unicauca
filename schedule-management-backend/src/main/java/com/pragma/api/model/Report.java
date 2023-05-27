package com.pragma.api.model;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pragma.api.model.enums.DaysEnumeration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    
    // Atributos de la tabla Schedule
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DaysEnumeration day;

    @Column(name = "starting_time")
    private LocalTime startingTime;
    @Column(name = "ending_time")
    private LocalTime endingTime;

    @Column(name = "starting_Date")
    private Date startingDate;

    @Column(name = "ending_Date")
    private Date endingDate;

    @Column(name ="is_reserve")
    private boolean isReserve;

    // Relaciones con otras tablas
    @ManyToOne
    @JoinColumn(name = "environment_id")
    private Environment environment;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

}
