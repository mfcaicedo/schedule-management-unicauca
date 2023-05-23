package com.pragma.api.util.file.templateclasses;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowEnvironment {

    private Integer rowNum;
    private String name;
    private String location;
    private Integer capacity;
    private String availableResources;
    private String environmentType;
    private String faculty;

    private List<Integer> quantity;

}
