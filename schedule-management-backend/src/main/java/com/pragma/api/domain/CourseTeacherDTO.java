package com.pragma.api.domain;

import com.pragma.api.model.Course;
import com.pragma.api.model.Person;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseTeacherDTO {

    private Integer id;

    private TeacherCategoryEnumeration teacherCategory;

    private CourseDTO course;

    private PersonDTO person;

}
