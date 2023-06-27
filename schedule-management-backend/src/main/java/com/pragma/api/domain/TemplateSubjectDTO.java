package com.pragma.api.domain;

import com.pragma.api.model.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
public class TemplateSubjectDTO {
    private Integer id;

    private String nameFile;

    private byte[] file;
    private Subject subjectFile;
}
