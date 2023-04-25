package com.pragma.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Program")
public class Program {
    @Id
    @Column(name = "program_id", length = 30)
    private String programId;
    @Column(name = "name")
    private String name;

    @Column(name="color")
    private String color;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "program")
    private Set<Subject> subjects;

    //relacion 1 a 1 con AcademicOfferFile
    @OneToOne(mappedBy = "program", cascade = CascadeType.ALL)
    private AcademicOfferFile academicOfferFile;

    //Relacion de muchos a 1 con Teacher Descripcion: indica que un profesor es Coordinador un Programa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_code")
    private Teacher teacher;

    //Relacion de 1 a muchos con Teacher Descripcion: indica que un programa tiene muchos profesores
    @OneToMany(mappedBy = "program")
    private Set<Teacher> teachers;






}
