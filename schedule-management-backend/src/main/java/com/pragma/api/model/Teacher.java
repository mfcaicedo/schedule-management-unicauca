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

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    /*
    //Relacion de muchos a muchos con Course Descripcion: indica que un profesor puede dar clases a 1 o varios cursos
    @ManyToMany(mappedBy = "assignedTeachers", fetch = FetchType.LAZY)
    private Set<Course> assignedSubjects; */
    @OneToMany(mappedBy = "teacher")
    private Set<CourseTeacher> assignedSubjects;

    //Relacion muchos a 1 con Department Descripcion: indica que un profesor pertenece a un departamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    //Relacion de 1 a 1 con Program Descripcion: indica que un profesor coordina a un programa
    @OneToOne(mappedBy = "teacher")
    private Program programHead;
    //Relacion de 1 a 1 con Department Descripcion: Relacion que un profesor es jefe de un departamento
    @OneToOne(mappedBy = "teacher")
    private Department departmentHead;
}
