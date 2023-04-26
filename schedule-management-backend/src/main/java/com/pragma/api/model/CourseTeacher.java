package com.pragma.api.model;

import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import com.pragma.api.model.serializables.CourseTeacherKey;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTeacher {
    @EmbeddedId
    CourseTeacherKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("teacherCode")
    @JoinColumn(name = "teacher_code")
    private Teacher teacher;
    @Column(name = "teacher_category")
    private TeacherCategoryEnumeration teacherCategory;

}
