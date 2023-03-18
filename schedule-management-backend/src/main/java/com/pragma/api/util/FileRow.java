package com.pragma.api.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRow {
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

    private String teacherCode;

    private String description;

    private String department;

}
