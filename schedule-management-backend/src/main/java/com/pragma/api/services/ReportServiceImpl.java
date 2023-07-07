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

import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.model.enums.DaysEnumeration;
import com.pragma.api.repository.IReportRepository;
import com.pragma.api.util.ValidatorUtil;

@Service
public class ReportServiceImpl implements IReportService{

    private final ModelMapper modelMapper;
    private final IReportRepository reportRepository;
    //private final ValidatorUtil validator;

    public ReportServiceImpl(ModelMapper modelMapper, IReportRepository reportRepository) {
        this.modelMapper = modelMapper;
        this.reportRepository = reportRepository;
        //this.validator = validator;

    }

    @Override
    public Response<List<ReportDTO>> getCombinetDataScheduleByEnvironmentId(Integer environmentId) {
        
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

        String courseGroup = (String) schedule[6];
        //Date endingDate = (Date) schedule[4];
        String environmentName= (String) schedule[7];
        String subjectName= (String) schedule[8];
        String programName= (String) schedule[9];
        String programColor= (String) schedule[10];
        
        ReportDTO reportDTO = new ReportDTO(scheduleId,dayTipe, localStartingTime, localendingTime, startingDate, endingDate, environmentName, subjectName, programName, programColor, courseGroup);
        ReportDTOList.add(reportDTO);
        }

        //return response;
        return ValidatorUtil.setResponse(ReportDTOList);
    
    }

    //Metodo para crear el reportDTO de reporte por facultad/programa y enviarlo a la api rest
    @Override
    public Response<List<ReportDTO>> getCombinetDataScheduleByProgramId(String programId) {
    
        List<Object[]> schedulesCombined = this.reportRepository.getCombinedDataScheduleByProgramId(programId);
        System.out.println("schedulesCombined: " + schedulesCombined);
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

        String subjectName= (String) schedule[6];
        int subjectSemester= (int) schedule[7];
        String courseGroup = (String) schedule[8];
        String courseDescription= (String) schedule[9];
        String programName= (String) schedule[10];
        String programColor= (String) schedule[11];
        String environmentName= (String) schedule[12];

        ReportDTO reportDTO = new ReportDTO(scheduleId, dayTipe, localStartingTime, localendingTime, startingDate, endingDate, subjectName, subjectSemester, courseGroup, courseDescription, programName, programColor, environmentName);
        ReportDTOList.add(reportDTO);
        }

        return ValidatorUtil.setResponse(ReportDTOList);
    }

    //Metodo para crear el reportDTO de reporte por semestre y enviarlo a la api rest
    @Override
    public Response<List<ReportDTO>> getCombinetDataScheduleByProgramIdSemester(String programId, Integer semester) {
    
        List<Object[]> schedulesCombined = this.reportRepository.getCombinedDataScheduleByProgramIdSemester(programId,semester);

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

        String subjectName= (String) schedule[6];
        int subjectSemester= (int) schedule[7];
        String courseGroup = (String) schedule[8];
        String courseDescription= (String) schedule[9];
        String programName= (String) schedule[10];
        String programColor= (String) schedule[11];
        String environmentName= (String) schedule[12];

        ReportDTO reportDTO = new ReportDTO(scheduleId, dayTipe, localStartingTime, localendingTime, startingDate, endingDate, subjectName, subjectSemester, courseGroup, courseDescription, programName, programColor, environmentName);
        ReportDTOList.add(reportDTO);
        }

        return ValidatorUtil.setResponse(ReportDTOList);
    }

    @Override
    public Response<List<ReportDTO>> getCombinedDataCoursePersonByPersonCode(String personCode) {
         
        List<Object[]> coursesCombined = this.reportRepository.getCombinedDataCoursePersonByPersonCode(personCode);

        List<ReportDTO> ReportDTOList = new ArrayList<>();
        for (Object[] coursePerson : coursesCombined) {
        
        //Casteo para el id de course_teacher    
        Integer coursePersonId = (Integer) coursePerson[0];
        //Long coursePersonId = bigInteger.longValue();    

        //Casteo nombre del docente
        String teacherName= (String) coursePerson[1];
        //Casteo para el id de program    
        String programId= (String) coursePerson[2];
        //Casteo dia
        String daysTypeString = (String) coursePerson[3];
        DaysEnumeration dayTipe = DaysEnumeration.valueOf(daysTypeString);

        //Casteo hora inicio y hora fin

        Time startingTime = (Time) coursePerson[4];

        LocalTime localStartingTime = startingTime.toLocalTime();

        Time endingTime = (Time) coursePerson[5];

        LocalTime localendingTime =endingTime.toLocalTime();
        

        //Nombre de materia
        String subjectName= (String) coursePerson[6];
        
        //grupo curso
        String courseGropu= (String) coursePerson[7];

        //nombre programa
        String programName= (String) coursePerson[8];
        
        //color
        String color= (String) coursePerson[9];
        
        //ambiente
        String environmentName= (String) coursePerson[10];

        ReportDTO reportDTO = new ReportDTO(coursePersonId, teacherName, programId, dayTipe, localStartingTime, localendingTime, subjectName, courseGropu, programName, color, environmentName);
        ReportDTOList.add(reportDTO);
        }
 
        return ValidatorUtil.setResponse(ReportDTOList);
    }
}