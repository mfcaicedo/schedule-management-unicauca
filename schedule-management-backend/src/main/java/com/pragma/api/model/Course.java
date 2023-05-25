package com.pragma.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "course")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;
    @Column(name = "course_group", nullable = false, length = 20)
    private String courseGroup;
    @Column(name = "course_capacity", nullable = false)
    private Integer courseCapacity;
    @Column(name = "remaining_hours", nullable = false)
    private Integer remainingHours;

    @Column(name = "description")
    private String description;

    public Course(String courseGroup, Integer courseCapacity, Integer remainingHours, String description) {
        this.courseGroup = courseGroup;
        this.courseCapacity = courseCapacity;
        this.remainingHours = remainingHours;
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_code")
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private Set<CourseTeacher> assignedTeachers;

    @OneToMany(mappedBy = "course")
    private Set<Schedule> schedules;
}
