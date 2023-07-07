package com.pragma.api.domain;

import com.pragma.api.model.AcademicOfferFile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
public class TemplateFileDTO {
    private Integer id;

    private String nameFile;

    private byte[] file;
    private AcademicOfferFile offerFile;
}
