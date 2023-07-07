package com.pragma.api.domain;

import com.pragma.api.model.enums.ResourceTypeEnumeration;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ResourceTypeEnumeration resourceType;
}
