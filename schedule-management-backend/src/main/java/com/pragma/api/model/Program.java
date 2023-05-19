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

    //relacion 1 a * con AcademicOfferFile
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private Set<AcademicOfferFile> academicOfferFile;

    //Relacion de muchos a 1 con Teacher Descripcion: indica que un departamento es coordinado por un profesor
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_code")
    private Teacher teacher;

}
