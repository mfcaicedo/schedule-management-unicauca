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
public class Department {
    @Id
    @Column(name = "department_id", length = 20)
    private String departmentId;
    @Column(name = "department_name", length = 40)
    private String departmentName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    //relacion 1 a muchos con Teacher Descripcion: indica que un departamento tiene muchos profesores
    @OneToMany(mappedBy = "department")
    private Set<Teacher> teachers;

    //relacion de muchos a 1 con Department Descripcion: Relacion que un profesor es jefe de uno o varios departamentos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_code")
    private Teacher teacher;

    @OneToMany(mappedBy = "department")
    private Set<Program> programs;
}
