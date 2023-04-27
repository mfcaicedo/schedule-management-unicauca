package com.pragma.api.model.serializables;

import javax.persistence.Column;
import java.io.Serializable;

public class CourseTeacherKey implements Serializable {
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "teacher_code")
    private String teacherCode;

    public CourseTeacherKey() {
    }
    public CourseTeacherKey(Integer courseId, String teacherCode) {
        this.courseId = courseId;
        this.teacherCode = teacherCode;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    /**
     * metodo equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseTeacherKey)) return false;
        CourseTeacherKey that = (CourseTeacherKey) o;
        return getCourseId().equals(that.getCourseId()) && getTeacherCode().equals(that.getTeacherCode());
    }
    /*
     * metodo hashcode
     */
    @Override
    public int hashCode() {
        return 0;
    }

}
