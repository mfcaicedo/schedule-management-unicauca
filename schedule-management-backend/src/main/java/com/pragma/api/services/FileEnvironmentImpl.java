package com.pragma.api.services;



import com.pragma.api.model.*;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.repository.IResourceRepository;
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


    private IEnvironmentRepository environmentRepository;

    private IFacultyRepository facultyRepository;

    private IResourceRepository resourceRepository;

    @Autowired
    public FileEnvironmentImpl(IEnvironmentRepository environmentRepository, IFacultyRepository facultyRepository,
                               IResourceRepository resourceRepository) {
        this.environmentRepository = environmentRepository;
        this.facultyRepository = facultyRepository;
        this.resourceRepository= resourceRepository;
    }

    @Override
    public List<String> uploadFile(MultipartFile file) throws IOException {
        FileEnvironment fileEnvironment = new FileEnvironment();
        List<FileRowEnvironment> logs = fileEnvironment.getLogs(file);
        if(logs==null){
            List<String> infoLogs = new ArrayList<>();
            infoLogs.add("empty field, check the file");
            return infoLogs;
        }
        return processFile(logs);

    }

    @Override
    public List<String> processFile(List<FileRowEnvironment> logs) {
        List<String> infoLogs = new ArrayList<>();

        for(FileRowEnvironment log:logs){

            Faculty faculty = facultyRepository.findByFacultyIdIs(log.getFaculty().toUpperCase().trim());

            if(faculty != null){

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

                if(!log.getAvailableResources().toUpperCase().trim().equals("POR DEFINIR")){
                    List<Resource> resourcesDb = this.resourceRepository.findAll();
                    String[] words = log.getAvailableResources().split(",");
                    String[] wordsFormat = wordFormat(words); //formatear palabras
                    // agrega los recursos disponibles
                    environment.setAvailableResources(verifyResources(wordsFormat, resourcesDb));
                    if(warningForResources(verifyResources(wordsFormat,resourcesDb),wordsFormat)){
                        infoLogs.add("Warning, not all needed resources are added");
                    }else{
                        infoLogs.add("successful, all resources added");
                    }
                }

                environment.setFaculty(faculty);
                environmentRepository.save(environment);
                infoLogs.add("Environment Created ");
            }else{
                infoLogs.add("Environment NOT Created, verify fields");
            }
        }
        return infoLogs;
    }

    private String [] wordFormat(String [] words){
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase().trim();
        }
        return words;
    }

    private Set<Resource> verifyResources(String[] wordsFormat, List<Resource> resourcesDb){

        Set<Resource> resources = new HashSet<>();
        for (int i = 0; i < wordsFormat.length; i++) {
            for (int j=0 ;j < resourcesDb.size(); j++) {
                if (resourcesDb.get(j).getName().toUpperCase().trim().equals(wordsFormat[i])) {
                    Resource resource = resourcesDb.get(j);
                    resources.add(resource);

                    break;
                }
            }
        }

        return resources;
    }

    private boolean warningForResources(Set<Resource> resources, String[] wordsFormat){
        if(resources.size() != wordsFormat.length){
            return true;
        }
        return false;
    }
}
