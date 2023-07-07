package com.pragma.api.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.CoursePersonDTO;
import com.pragma.api.domain.Response;
//import com.pragma.api.model.CoursePerson;
import com.pragma.api.model.CourseTeacher;
import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import com.pragma.api.repository.ICoursePersonRepository;

@Service
public class CoursePersonServiceImpl implements ICoursePersonService{


    private final ModelMapper modelMapper;

    private final ICoursePersonRepository coursePersonRepository;

    @Autowired
    public CoursePersonServiceImpl(ModelMapper modelMapper, ICoursePersonRepository coursePersonRepository) {
        this.modelMapper = modelMapper;
        this.coursePersonRepository = coursePersonRepository;
    }

/*
    @Override
    public TeacherCategoryEnumeration convertIntToEnum(Integer teacherCategory) {
        TeacherCategoryEnumeration category = TeacherCategoryEnumeration.OTHER;
        switch(teacherCategory){
            case 1:
                category = TeacherCategoryEnumeration.PRIMARY;
                break;
            case 2:
                category = TeacherCategoryEnumeration.SECONDARY;
                break;
            default:
                category = TeacherCategoryEnumeration.OTHER;
        }

        return category;
        
    }

 
    private List<CoursePerson> convertAllIntsToEnums(List<CourseTeacher> courses){
        List<CoursePerson> convertedCourses = null;
        for(int i = 0; i<courses.size(); i++){
            convertedCourses.add(new CoursePerson(courses.get(i).getId(), convertIntToEnum(courses.get(i).getTeacherCategory()), courses.get(i).getCourse(), courses.get(i).getPerson()));
        }
        return convertedCourses;
    }
*/
    @Override
    public Response<List<CoursePersonDTO>> findAllByTeacherCode(String teacherCode) {
        List<CourseTeacher> courses = this.coursePersonRepository.findAllByTeacherCode(teacherCode);
        //List<CoursePerson> convertedCourses = convertAllIntsToEnums(courses);
        List<CoursePersonDTO> coursesDTOlist = modelMapper.map(courses,new TypeToken<List<CoursePersonDTO>>() {}.getType());
        Response<List<CoursePersonDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of Course_persons Finded successfully");
        response.setDeveloperMessage("List of Course_persons Finded successfully");
        response.setMoreInfo("localhost:8081/api/CoursePerson(toDO)");
        response.setErrorCode("");
        response.setData(coursesDTOlist);
        return response;
    }
    
}
