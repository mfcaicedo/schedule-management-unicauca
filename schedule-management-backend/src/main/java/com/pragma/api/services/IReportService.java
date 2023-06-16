package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;


public interface IReportService {
    
    //Metodo que devuelve una lista con ls datos combinados de schedule,
    // para poder hacer el reporte por salon
    Response<List<ReportDTO>> getCombinetDataScheduleByEnvironmentId(Integer environmentId);
    
    //List<ReportDTO> getCombinetDataScheduleByEnvironmentId(Integer environmentId);

    //Metodo que devuelve una lista con ls datos combinados de schedule,
    // para poder hacer el reporte por facultad/Programa mediante id de programa
    Response<List<ReportDTO>> getCombinetDataScheduleByProgramId(String programId);

    //Metodo que devuelve una lista con ls datos combinados de schedule,
    // para poder hacer el reporte por semestre mediante id de programa y semestre
    Response<List<ReportDTO>> getCombinetDataScheduleByProgramIdSemester(String programId, Integer semester);

    //Metodo para obtener el reporte de profesor por codigo del profesor
    Response<List<ReportDTO>> getCombinedDataCoursePersonByPersonCode(String personCode);
    
}


