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
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;


}
