package com.pragma.api.services;

import java.util.List;

import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;


public interface IReportService {
    
    //Metodo que devuelve una lista con ls datos combinados de schedule,
    // para poder hacer el reporte por salon
    Response<List<ReportDTO>> getCombinetDataScheduleByEnvironmentId(Integer environmentId);
    
    //List<ReportDTO> getCombinetDataScheduleByEnvironmentId(Integer environmentId);
    
}


