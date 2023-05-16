package com.pragma.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "template_file")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_file_id")
    private Integer id;

    @Column(name = "file_name", nullable = false, length = 80)
    private String nameFile;

    @Column(name = "file", nullable = false )
    @Lob
    private byte[] file;

    @OneToOne(mappedBy = "templateFile")
    private AcademicOfferFile academicOfferFile;
}
