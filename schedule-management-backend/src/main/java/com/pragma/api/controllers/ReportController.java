package com.pragma.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.ProgramDTO;
import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.services.ICoursePersonService;
import com.pragma.api.services.IDepartmentService;
import com.pragma.api.services.IPersonService;
import com.pragma.api.services.IProgramService;
import com.pragma.api.services.IReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {
    
    private final IReportService reportService;
    private final IDepartmentService departmentService;
    private final IProgramService programService;
    private final ICoursePersonService coursePersonService;
    private final IPersonService personService;

    @GetMapping("/programByDepartmentId/{departmentId}")
    public Response<List<ProgramDTO>> findProgramsByDepartmentId(@PathVariable String departmentId){
        return this.programService.findAllByDepartmentId(departmentId);
    }

    @GetMapping("/departmentsByFacultyId/{facultyId}")
    public Response<List<DepartmentDTO>> findDepartmentsByFacultyId(@PathVariable String facultyId){
        return this.departmentService.findAllByFacultyId(facultyId);
    }


    public ReportController(IReportService reportService, IDepartmentService departmentService, IProgramService programService, ICoursePersonService coursePersonService, IPersonService personeService) {
        this.reportService = reportService;
        this.departmentService = departmentService;
        this.programService = programService;
        this.coursePersonService = coursePersonService;
        this.personService = personeService;
    }


    //Consulta para traer todos los datos de schedule combinados , para hacer el reporte pertinente pasa salon
    @GetMapping("/byEnvironmentId/{environmentId}")
    public Response<List<ReportDTO>> findAllByEnvironmentId(@PathVariable Integer environmentId){
        return this.reportService.getCombinetDataScheduleByEnvironmentId(environmentId);
    }

    //Consulta para traer todos los datos de schedule combinados , para hacer el reporte pertinente para facultad/programa
    @GetMapping("/byprogramId/{program_id}")
    public Response<List<ReportDTO>> findAllByProgramId(@PathVariable String program_id){
        return this.reportService.getCombinetDataScheduleByProgramId(program_id);
    }

    //Consulta para traer todos los datos de schedule combinados , para hacer el reporte pertinente para semestre
    @GetMapping("/byprogramIdSemester/{program_id}/{semester}")
    public Response<List<ReportDTO>> findAllByProgramIdSemester(@PathVariable String program_id,@PathVariable Integer semester){
        return this.reportService.getCombinetDataScheduleByProgramIdSemester(program_id,semester);
    }

    @GetMapping("/byPersonCode/{personCode}")
    public Response<List<ReportDTO>> findAllByPersonCode(@PathVariable String personCode){
        return this.reportService.getCombinedDataCoursePersonByPersonCode(personCode);
    }

    @GetMapping("/teacherByDeptId/{departmentId}")
    public Response<List<PersonDTO>> findAllPersonByDepartmentId(@PathVariable String departmentId){
        return this.personService.findAllTeachersByDepartmentId(departmentId);
    }
  
    @GetMapping("/teacherByName/{full_name}")
    public Response<List<PersonDTO>> findTeacheByName(@PathVariable String full_name){
        return this.personService.findAllTeachersByName(full_name);
    }
}
