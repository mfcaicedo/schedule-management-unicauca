package com.pragma.api.model;

import lombok.*;

import javax.persistence.*;

import com.pragma.api.model.enums.PersonTypeEnumeration;

import java.util.Set;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @Column(length = 45)
    private String personCode;
    @Column(name = "full_name", length = 80)
    private String fullName;

    /*
    * Relacion de muchos a muchos con Course Descripcion: indica que un profesor puede dar clases a 1 o varios cursos
    */
    @OneToMany(mappedBy = "person")
    private Set<CourseTeacher> assignedSubjects;

    //Relacion muchos a 1 con Department Descripcion: indica que un profesor pertenece a un departamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    //Relacion de 1 a 1 con Program Descripcion: indica que un profesor coordina a un programa
    @OneToOne(mappedBy = "person")
    private Program programHead;
    //Relacion de 1 a 1 con Department Descripcion: Relacion que un profesor es jefe de un departamento
    @OneToOne(mappedBy = "person")
    private Department departmentHead;

    //dominio que indica si la persona es un profesor o un administrativo
    @Column(name = "person_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonTypeEnumeration personType;

}
