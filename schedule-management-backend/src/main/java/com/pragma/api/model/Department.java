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

    @OneToMany(mappedBy = "department")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "department")
    private Set<Program> programs;
}
