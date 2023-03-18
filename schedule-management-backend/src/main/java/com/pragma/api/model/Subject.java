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
public class Subject {
    @Id
    @Column(name = "subject_code",length = 30)
    private String subjectCode;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(name = "weekly_overload", nullable = false)
    private Integer weeklyOverload;
    @Column(name = "time_block", nullable = false)
    private Boolean timeBlock;
    @Column(nullable = false)
    private Integer semester;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id")
    private Program program;
    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;
}
