package com.pragma.api.model;

import com.pragma.api.model.enums.PersonCategoryEnumeration;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_person_id")
    private Integer id;

    @Column(name = "person_category")
    private PersonCategoryEnumeration personCategory;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "personCode")
    private Person person;

}
