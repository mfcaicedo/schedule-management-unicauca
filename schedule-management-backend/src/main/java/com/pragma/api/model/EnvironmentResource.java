package com.pragma.api.model;

import com.pragma.api.model.enums.TeacherCategoryEnumeration;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "environment_resource")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvironmentResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "environment_resource_id")
    private Integer id;

    @Column (name = "resource_total")
    private Integer resourceTotal;


    @ManyToOne
    @JoinColumn(name = "environment_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "resourceCode")
    private Teacher teacher;

}
