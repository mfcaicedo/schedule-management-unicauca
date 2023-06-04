package com.pragma.api.controllers;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.api.domain.CoursePersonDTO;
import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.TeacherReportDTO;
import com.pragma.api.services.ICoursePersonService;
import com.pragma.api.services.IDepartmentService;
import com.pragma.api.services.IProgramService;


@RestController
@RequestMapping("/teacherReport")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherReportController {
    
    private final IDepartmentService departmentService;
    private final IProgramService programService;
    private final ICoursePersonService coursePersonService;

    public TeacherReportController(IDepartmentService departmentService, IProgramService programService, ICoursePersonService coursePersonService) {
        this.departmentService = departmentService;
        this.programService = programService;
        this.coursePersonService = coursePersonService;
    }

    @GetMapping("/programByDepartmentId/{departmentId}")
    public Response<List<ProgramDTO>> findProgramsByDepartmentId(@PathVariable Integer departmentId){
        return this.programService.findAllByDepartmentId(departmentId);
    }

    @GetMapping("/departmentsByFacultyId/{facultyId}")
    public Response<List<DepartmentDTO>> findDepartmentsByFacultyId(@PathVariable String facultyId){
        return this.departmentService.findAllByFacultyId(facultyId);
    }

    //Puede que no se necesite este metodo despues de todo, mientras se verifica se deja comentado
    /*
    @GetMapping("/coursesPersonByTeacherCode/{teacherCode}")
    public Response<List<CoursePersonDTO>> findCoursesPersonByTeacherCode(@PathVariable String teacherCode){
        return this.coursePersonService.findAllByTeacherCode(teacherCode);
    }
    */
    /* 
    //Metodo para solocitar el reporte del profesor, aun en construccion
    @GetMapping("/getById/{teacherCode}")
    public Response<TeacherReportDTO> getReport(@PathVariable String teacheString){
        TeacherReportDTO response;
        return response;
    }
    */
}
