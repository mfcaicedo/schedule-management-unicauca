package com.pragma.api.domain;

import com.pragma.api.model.Period;
import com.pragma.api.model.Program;
import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicOfferFileDTO {

    private Integer id;
    private String nameFile;
    private Date dateRegisterFile;
    private StateAcOfferFileEnumeration stateFile;
    //private Byte[] file;
    private Period period;
    private Program program;



}
