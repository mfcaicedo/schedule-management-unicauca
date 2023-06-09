package com.pragma.api.util.file.templateclasses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowSubject {

    private Integer rowNum;
    private String subjectCode;
    private String name;
    private Integer semester;
    private String inBlock;
    private Integer weeklyOverload;
    private String programCode;
}
