package com.pragma.api.util.file.templateclasses;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileRowEnvironment {
    private String name;
    private String location;
    private Integer capacity;
    private String availableResources;
    private String environmentType;
    private String faculty;
}
