package com.pragma.api.util.file.templateclasses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowTeacher {

    private Integer rowNum;
    private Integer code_teacher;
    private String name_teacher;
    private String name_department;

}
