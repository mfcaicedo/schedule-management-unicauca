package com.pragma.api.business;



import com.pragma.api.model.*;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.model.enums.ResourceTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.util.file.FileEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileEnvironmentImpl implements IFileEnvironmentService{

    //VERIFICAAAAR
    @Autowired
    private IEnvironmentRepository environmentRepository;
    @Autowired
    private IFacultyRepository facultyRepository;

    @Override
    public List<String> uploadFile(MultipartFile file) throws IOException {
        FileEnvironment fileEnvironment = new FileEnvironment();
        List<FileRowEnvironment> logs = fileEnvironment.getLogs(file);
        return processFile(logs);

    }

    @Override
    public List<String> processFile(List<FileRowEnvironment> logs) {
        List<String> infoLogs = new ArrayList<>();

        for(FileRowEnvironment log:logs){


            Faculty faculty = facultyRepository.findByFacultyIdIs(log.getFaculty());
            if(faculty!=null){

                Environment environment = new Environment();
                environment.setName(log.getName());
                environment.setLocation(log.getLocation());
                environment.setCapacity(log.getCapacity());
                switch (log.getEnvironmentType().toUpperCase()){
                    case "AUDITORIO":
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.AUDITORIO);
                        break;
                    case "LABORATORIO":
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.LABORATORIO);
                        break;
                    case "SALON":
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.SALON);
                        break;
                    default:
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.DEFAULT);
                        break;
                }

                Set<Resource> Resources = new HashSet<>();
                String[] words = log.getAvailableResources().split(",");
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].toUpperCase().trim();
                    Resource resource = new Resource();
                    resource.setName(words[i]);
                    if((words[i].equals("TELEVISOR"))||(words[i].equals("VIDEOBEAM"))){
                        resource.setResourceType(ResourceTypeEnumeration.TECNOLOGICO);
                    }else{
                        resource.setResourceType(ResourceTypeEnumeration.PEDAGOGICO);
                    }
                    Resources.add(resource);
                }

                environment.setAvailableResources(Resources);
                environment.setFaculty(faculty);
                environmentRepository.save(environment);

                infoLogs.add("Course Created succesfully!");
            }else{
                infoLogs.add("Course NOT Created");
            }
        }
        return infoLogs;
    }
}
