package com.pragma.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @Column(length = 45)
    private String teacherCode;
    @Column(name = "full_name", length = 80)
    private String fullName;


    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    //Relacion muchos a 1 con Department Descripcion: indica que un profesor pertenece a un departamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    //Relacion 1 a muchos con Department Descripcion: indica que un profesor puede ser jefe de 1 o varios departamentos
    @OneToMany(mappedBy = "teacher")
    private Set<Department> departments;

    //Relacion Muchos a uno con Program Descripcion: indica que un profesor da clases a un programa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id")
    private Program program;

    //Relacion de 1 a muchos con Program Descripcion: indica que un profesor coordina a 1 o varios programas
    @OneToMany(mappedBy = "teacher")
    private Set<Course> programs;


}
