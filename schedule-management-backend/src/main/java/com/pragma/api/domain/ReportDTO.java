package com.pragma.api.domain;

import java.sql.Date;
import java.time.LocalTime;

import com.pragma.api.model.enums.DaysEnumeration;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {
    
    private Long id;
    private DaysEnumeration day;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private Date startingDate;
    private Date endingDate;
    private String environmentName;
    private String subjectName;
    private String programName;
    private String programColor;
    private String courseGroup;
    private String courseDescription;
    private int subjectSemester;

    // Agrega los demás campos correspondientes a las otras tablas

    //Constructor para hacer reporte por salon
    public ReportDTO(Long id,DaysEnumeration day, LocalTime startingTime, LocalTime endingTime, Date startingDate, Date endingDate,String environmentName, String subjectName, String programName,String programColor) {
        this.id=id;
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.environmentName=environmentName;
        this.subjectName = subjectName;
        this.programName = programName;
        this.programColor=programColor;
    }

    //Constructor para hacer reporte por Facultad/programa
    public ReportDTO(Long id, DaysEnumeration day, LocalTime startingTime, LocalTime endingTime, Date startingDate, Date endingDate, String subjectName, int subjectSemester, String courseGroup, String courseDescription, String programName, String programColor, String environmentName) {
        this.id = id;
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.subjectName = subjectName;
        this.subjectSemester = subjectSemester;
        this.courseGroup = courseGroup;
        this.courseDescription = courseDescription;
        this.programName = programName;
        this.programColor = programColor;
        this.environmentName = environmentName;
    }

}
