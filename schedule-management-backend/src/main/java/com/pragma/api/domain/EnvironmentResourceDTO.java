package com.pragma.api.domain;

import com.pragma.api.model.Environment;
import com.pragma.api.model.Resource;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentResourceDTO {
    private Integer id;

    private Integer resourceQuantity;

    private Environment environment;

    private Resource resource;

    public EnvironmentResourceDTO(Integer resourceQuantity, Environment environment, Resource resource){
        this.resourceQuantity = resourceQuantity;
        this.environment = environment;
        this.resource = resource;
    }
}
