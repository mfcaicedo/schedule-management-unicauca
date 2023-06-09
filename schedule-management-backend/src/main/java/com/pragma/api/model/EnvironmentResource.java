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

    @Column (name = "resource_quantity")
    private Integer resourceQuantity;


    @ManyToOne
    @JoinColumn(name = "environment_id")
    private Environment environment;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public EnvironmentResource(Integer resourceQuantity,Environment environment,Resource resource){
        this.resourceQuantity = resourceQuantity;
        this.environment = environment;
        this.resource = resource;
    }

}
