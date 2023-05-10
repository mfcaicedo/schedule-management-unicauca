package com.pragma.api.util.file.templateclasses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowAcademicOffer {
    private String period;

    private String program;

    private Integer semester;

    private String subjectCode;

    private String subjectName;

    private String group;

    private Integer capacity;

    private Integer dailyOverload;
    private Integer weeklyOverload;

    private String environment;

    private String personCode;

    private String description;

    private String department;

}
