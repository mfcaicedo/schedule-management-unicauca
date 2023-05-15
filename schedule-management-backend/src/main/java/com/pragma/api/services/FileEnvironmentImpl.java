package com.pragma.api.services;



import com.pragma.api.domain.EnvironmentResourceDTO;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IEnvironmentResourceRepository;
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
    @Autowired
    private IEnvironmentResourceService iEnvironmentResourceService;

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
                    case "EDIFICIO":
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.EDIFICIO);
                        break;
                    default:
                        environment.setEnvironmentType(EnvironmentTypeEnumeration.DEFAULT);
                        break;
                }

                //if(log.getAvailableResources().equals("no aplica")){
                //    environment.setAvailableResources(null);
                //}else{
                //if(!log.getAvailableResources().toUpperCase().trim().equals("POR DEFINIR")){
                //    List<Resource> resourcesDb = this.resourceRepository.findAll();
                //    String[] words = log.getAvailableResources().split(",");
                //    String[] wordsFormat = wordFormat(words); //formatear palabras
                    // agrega los recursos disponibles
                    //environment.setAvailableResources(verifyResources(wordsFormat, resourcesDb));
                    //if(warningForResources(verifyResources(wordsFormat,resourcesDb),wordsFormat)){
                    //    infoLogs.add("Warning, not all needed resources are added");
                    //}else{
                  //      infoLogs.add("successful, all resources added");
                    //}
                //}
                
                //}
                environment.setFaculty(faculty);
                if(log.getLocation().equals(null)){
                    environment.setParentEnvironment(null);
                }else {
                    List<Environment> enviromentsDb = this.environmentRepository.findAll();
                    environment.setParentEnvironment(selectParent(log.getLocation().toString(), enviromentsDb));
                }
                environmentRepository.save(environment);
                if(log.getAvailableResources()!=null){
                    //Se crea el enviromentResources
                    List<Resource> resourcesDb = this.resourceRepository.findAll();
                    System.out.println("-----que hay:"+log.getAvailableResources());
                    String[] words = log.getAvailableResources().split(",");
                    String[] wordsFormat = wordFormat(words); //formatear palabras
                    System.out.println("---words:"+words);
                    System.out.println("---words:"+wordsFormat);
                    List<Resource> resources = new ArrayList<>();
                    resources = verifyResources(wordsFormat, resourcesDb);
                    System.out.println("resources"+resources.size());
                    System.out.println("resources: "+resources.get(0).getName());
                    System.out.println("environment: "+environment.getName());
                    System.out.println("log quantity: "+log.getQuantity().get(0));

                    for (int i = 0; i < resources.size(); i++) {

                        EnvironmentResourceDTO environmentResource = new EnvironmentResourceDTO(
                                log.getQuantity().get(i),environment,resources.get(i));
                        this.iEnvironmentResourceService.saveEnvironmentResource(environmentResource);
                    }
                }

                



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
    
    private List<Resource> verifyResources(String[] wordsFormat, List<Resource> resourcesDb){
        System.out.println("resource db: " + resourcesDb.get(0).getName());
        System.out.println("resource tamanio: " + resourcesDb.size());


        List<Resource> resources = new ArrayList<>();
        //Set<EnvironmentResource> environmentResources = new HashSet<>();
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
        //return Resources;
    }

    private Environment selectParent(String ubicacion, List<Environment> environmentsDb) {

        Environment environmentP = null;
        for (int i = 0; i < environmentsDb.size(); i++) {
            if(environmentsDb.get(i).getName().equals(ubicacion)){
                environmentP = environmentsDb.get(i);
            }
        }

        return environmentP;
    }

    private boolean warningForResources(Set<EnvironmentResource> resources, String[] wordsFormat){
        if(resources.size() != wordsFormat.length){
            return true;
        }
        return false;
    }
}
