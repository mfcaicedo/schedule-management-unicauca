package com.pragma.api.domain;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceList {
    private List<Integer> resourceList;
}
