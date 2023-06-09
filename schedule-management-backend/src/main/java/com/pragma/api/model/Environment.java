package com.pragma.api.model;

import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "environment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="environment_id")
    private Integer id;
    @Column(length = 45)
    private String name;
    @Column(length = 60, nullable = true)
    private String location;
    @Column(nullable = true)
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnvironmentTypeEnumeration environmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    /*
    @ManyToMany
    @JoinTable(
            name = "available_resources",
            joinColumns = @JoinColumn(name = "environment_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> availableResources;
    */
    @OneToMany(mappedBy = "environment")
    private Set<EnvironmentResource> availableResources;

    @OneToMany(mappedBy = "environment", fetch = FetchType.LAZY)
    private Set<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Environment parentEnvironment;
    @OneToMany(mappedBy = "parentEnvironment", fetch = FetchType.LAZY)
    private Set<Environment> subEnvironments;

    public Environment(String name, String location, Integer capacity, Faculty faculty) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.faculty = faculty;
    }
    //Constructor personalizado para poder realizar la consulta y cargar el tipo de ambiente
    public Environment(Integer id, String name, EnvironmentTypeEnumeration environmentType) {
        this.id = id;
        this.name = name;
        this.environmentType = environmentType;
    }
}