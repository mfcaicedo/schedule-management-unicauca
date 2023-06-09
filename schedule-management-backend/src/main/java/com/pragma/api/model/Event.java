package com.pragma.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pragma.api.model.enums.EventTypeEnumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;
    @Column(name = "event_manager_name", nullable = false)
    private String eventManagerName;
    @Column(name = "description")
    private String description;

    /* 
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_code")
    private Person person;

    @OneToMany(mappedBy = "event")
    private Set<Schedule> schedules;

    @Column(name = "event_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private EventTypeEnumeration eventType;

}
