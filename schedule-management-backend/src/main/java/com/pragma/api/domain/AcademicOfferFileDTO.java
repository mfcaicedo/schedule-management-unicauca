package com.pragma.api.domain;

import com.pragma.api.model.Program;
import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class AcademicOfferFileDTO {

    private Integer id;
    private String nameFile;
    private Integer dateRegisterFile;
    private StateAcOfferFileEnumeration stateFile;
    private Byte[] file;
    private Program program;

}
