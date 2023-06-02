package com.pragma.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @Column(name = "faculty_id", length = 20)
    private String facultyId;
    @Column(name = "faculty_name", nullable = false, unique = true)
    private String facultyName;

    //@OneToMany(mappedBy = "faculty")
    //private Set<Department> departments;

    //@OneToMany(mappedBy = "faculty")
    //private Set<Environment> environments;
}
