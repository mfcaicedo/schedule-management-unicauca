package com.pragma.api.services;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.Report;
import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.repository.IReportRepository;
import com.pragma.api.util.exception.ScheduleBadRequestException;

@Service
public class ReportServiceImpl implements IReportService{

    private final ModelMapper modelMapper;
    private final IReportRepository reportRepository;

    public ReportServiceImpl(ModelMapper modelMapper, IReportRepository reportRepository) {
        this.modelMapper = modelMapper;
        this.reportRepository = reportRepository;
        
    }

    @Override
    public Response<List<ReportDTO>> getCombinetDataScheduleByEnvironmentId(Integer environmentId) {
        //Acordarse de cambiar el mensaje de la excepcion porque necesitamos uno de ambiente
        //if(!this.reportRepository.existsBy()) throw  new ScheduleBadRequestException("bad.request.event.event_name","");
        
        List<Object[]> schedulesCombined = this.reportRepository.getCombinedDataScheduleByEnvironmentId(environmentId);

        List<ReportDTO> ReportDTOList = new ArrayList<>();
        for (Object[] schedule : schedulesCombined) {
        
        //Casteo para el id de schedule    
        BigInteger bigInteger = (BigInteger) schedule[0];
        Long scheduleId = bigInteger.longValue();    

        String daysTypeString = (String) schedule[1];
        DaysEnumeration dayTipe = DaysEnumeration.valueOf(daysTypeString);

        //Para conversion a local date, casteo
        Time startingTime = (Time) schedule[2];

        LocalTime localStartingTime = startingTime.toLocalTime();

        Time endingTime = (Time) schedule[3];

        LocalTime localendingTime =endingTime.toLocalTime();

        //LocalTime endingTime = (LocalTime) schedule[2];

        //Casteo para la fechas startingDate
        Timestamp timestamp = (Timestamp) schedule[4];
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        Date startingDate = Date.valueOf(localDate);


        
        //Casteo para la fechas endingDate
        Timestamp timestamp1 = (Timestamp) schedule[5];
        LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
        LocalDate localDate1 = localDateTime1.toLocalDate();
        Date endingDate = Date.valueOf(localDate1);

        //Date endingDate = (Date) schedule[4];
        String environmentName= (String) schedule[6];
        String subjectName= (String) schedule[7];
        String programName= (String) schedule[8];
        String programColor= (String) schedule[9];

        ReportDTO reportDTO = new ReportDTO(scheduleId,dayTipe, localStartingTime, localendingTime, startingDate, endingDate, environmentName, subjectName, programName, programColor);
        ReportDTOList.add(reportDTO);
        }


        Response<List<ReportDTO>> response = new Response<>();
        response.setStatus(200);
        response.setUserMessage("List of schedules Finded successfully");
        response.setDeveloperMessage("List of schedules Finded successfully");
        response.setMoreInfo("localhost:8081/api/report(toDO)");
        response.setErrorCode("");
        response.setData(ReportDTOList);
        return response;

    
    }
}