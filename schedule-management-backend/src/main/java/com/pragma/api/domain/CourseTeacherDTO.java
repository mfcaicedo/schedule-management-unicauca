package com.pragma.api.domain;

import com.pragma.api.model.Course;
import com.pragma.api.model.Person;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseTeacherDTO {
    private Integer id;
    private TeacherCategoryEnumeration teacherCategory;
    private Course course;
    private Person person;
}
