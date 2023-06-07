package com.pragma.api.util.file.templateclasses;

import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowAcademicOffer {
    //Curso
    private Integer capacity;
    private String group;
    private String typeEnvironmentRequired; //Tipo o tipos de ambiente requerido
    private Integer weeklyOverload; //Horas restantes
    private Boolean inBlock; //En bloque
    private String subjectCode;
    //CourseTeacher
    private Integer courseId;
    private List<String> personCode; //codigos profesores
//    private String teacherCategory;
    //AcedemicOfferFile
    private Date dateRegisterFile;
    private String nameFile;
    private StateAcOfferFileEnumeration stateFile;
    private String period;
    private String programId;
}
