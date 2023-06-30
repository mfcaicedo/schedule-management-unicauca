package com.pragma.api.services;

import com.pragma.api.domain.CoursePersonDTO;
//import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;

import java.util.List;

public interface ICoursePersonService {
    //Buscar por id de facultad
    //public TeacherCategoryEnumeration convertIntToEnum(Integer teacherCategory);
    public Response<List<CoursePersonDTO>> findAllByTeacherCode(String teacherCode);
}
