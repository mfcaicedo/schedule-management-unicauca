package com.pragma.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.services.IReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {
    
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
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

}
