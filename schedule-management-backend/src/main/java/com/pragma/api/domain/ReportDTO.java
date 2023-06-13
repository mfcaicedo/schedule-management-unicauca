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

    // Agrega los demás campos correspondientes a las otras tablas

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

}
