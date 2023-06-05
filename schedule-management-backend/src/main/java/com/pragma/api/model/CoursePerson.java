package com.pragma.api.model;

import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import lombok.*;

import javax.persistence.*;
/*
@Entity
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursePerson {
    
   /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_teacher_id")
    */
    private Integer id;

    //@Column(name = "teacher_category")
    private TeacherCategoryEnumeration teacherCategory;
 
    //@ManyToOne
    //@JoinColumn(name = "course_id")
    private Course course;

    //@ManyToOne
    //@JoinColumn(name = "personCode")
    private Person person;

}