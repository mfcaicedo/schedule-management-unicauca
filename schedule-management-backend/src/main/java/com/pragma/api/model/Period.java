package com.pragma.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pragma.api.model.enums.PeriodStateEnumeration;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "period")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Period {
    @Id
    @Column(length = 40)
    private String periodId;
    @Enumerated(EnumType.STRING)
    private PeriodStateEnumeration state;
    @Column
    private Date initDate;
    @Column
    private Date endDate;

    public Period(String periodId, PeriodStateEnumeration state) {
        this.periodId = periodId;
        this.state = state;
    }

    @Getter(onMethod_= @JsonIgnore)
    @OneToMany(mappedBy = "period")
    private Set<AcademicOfferFile> academicOfferFiles;
}
