package com.pragma.api.model;

import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "academic_offer_file")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicOfferFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academic_offer_file_id")
    private Integer id;
    @Column(name = "name_file", nullable = false, length = 80)
    private String nameFile;
    @Column(name = "date_register_file", nullable = false)
    private Date dateRegisterFile;
    @Column(name = "state_file", nullable = false)
    private StateAcOfferFileEnumeration stateFile;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_file_id" )
    private TemplateFile templateFile;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "periodId")
    private Period period;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id")
    private Program program;


}
