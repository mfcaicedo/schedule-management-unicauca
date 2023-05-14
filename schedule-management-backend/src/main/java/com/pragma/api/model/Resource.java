package com.pragma.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resource{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Integer id;
    @Column(length = 40)
    private String name;
    @Column(name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceTypeEnumeration resourceType;
    /*
    @ManyToMany(mappedBy = "availableResources", fetch = FetchType.LAZY)
    private Set<Environment> resourceLocations;
     */
    @OneToMany(mappedBy = "resource")
    private Set<EnvironmentResource> resourceLocations;
}
