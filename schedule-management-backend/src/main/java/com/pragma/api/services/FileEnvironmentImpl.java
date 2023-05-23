package com.pragma.api.services;



import com.pragma.api.domain.EnvironmentResourceDTO;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.domain.enumStatusFile;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IEnvironmentResourceRepository;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.repository.IResourceRepository;
import com.pragma.api.util.file.FileEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
//import org.hibernate.mapping.Set;
import com.pragma.api.util.file.templateclasses.FileRowSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileEnvironmentImpl implements IFileEnvironmentService {

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
        this.resourceRepository = resourceRepository;

    }

    @Override
    public ResponseFile uploadFile(MultipartFile file) throws IOException {
        FileEnvironment fileEnvironment = new FileEnvironment();
        List<FileRowEnvironment> logs = fileEnvironment.getLogs(file);
        //if(logs==null){
        //    List<String> infoLogs = new ArrayList<>();
        //   infoLogs.add("empty field, check the file");
        //   return infoLogs;
        // }
        return processFile(logs);

    }

    @Override
    public ResponseFile processFile(List<FileRowEnvironment> logs) {

        ResponseFile responseFile = new ResponseFile();

        //----------------------------------------------------------------------
        List<String> EnvironmentNames = new ArrayList<>();
        List<FileRowEnvironment> fileEnvironment = new ArrayList<>();
        List<Environment> listEnvironment = new ArrayList<>();

        //----------------------------------------------------------------------

        //List<String> infoLogs = new ArrayList<>();
        int contRows = 0;
        int contSuccess = 0;
        int contError = 0;
        for (FileRowEnvironment log : logs) {

            Faculty faculty = facultyRepository.findByFacultyIdIs(log.getFaculty().toUpperCase().trim());
            Environment environment = new Environment();

            if (faculty == null) {
                responseFile.getLogsGeneric().add("[FILA "+ (log.getRowNum()+1) + "] LA FACULTAD ESTA VACIA");
            } else {
                int rowNum = log.getRowNum();
                if (rowNum != -1) {
                    contRows++;
                    rowNum = rowNum + 1;
                    boolean errorEnvironment = false;
                    boolean errorResources = false;
                    boolean errorVacias = false;
                    boolean errorTipos = false;
                    boolean errorRepetidos = false;

                    //---------------------------Name-----------------------------------
                    if (log.getName().trim().length() == 0) {
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("[FILA " + rowNum + "]  EL NOMBRE DEL AMBIENTE ESTA VACIO");
                    } else {
                        environment.setName(log.getName());
                    }

                    //-------------------------------Location-------------------------------

                    if (log.getLocation().trim().length() == 0) {
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE UBICACION ESTA VACIO");
                    } else {
                        if (log.getEnvironmentType().equals("EDIFICIO") && !(log.getLocation().toUpperCase().equals("NO APLICA"))) {
                            errorEnvironment = true;
                            responseFile.getLogsGeneric().add("FILA" + rowNum + "] SI ES EDIFICIO UBICACION DEBE DECIR 'NO APLICA'");

                        }else if (log.getLocation().toUpperCase().equals("NO APLICA")) {
                            environment.setParentEnvironment(null);
                        } else {
                            List<Environment> enviromentsDb = this.environmentRepository.findAll();
                            environment.setParentEnvironment(selectParent(log.getLocation().toString(), enviromentsDb));
                        }
                    }

                    //-----------------------------Capacity------------------------------
                    if (log.getCapacity() == -1) {
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("[FILA " + rowNum + "]  LA CAPACIDAD ESTA VACIA (CAPACIDAD OBLIGATORIA)");

                    //}
                    //sera colocar 0 en el excel?
                    //else if (log.getCapacity() == -2) {
                    //    responseFile.getLogsType().add("[FILA " + rowNum + "]  LA CAPACIDAD DEBE SER NUMERICA");
                    //    errorTipos = true;
                    } else {
                        environment.setCapacity(log.getCapacity());
                    }
                    //--------------------------Type-----------------------------------------

                    if (log.getEnvironmentType().trim().length() == 0) {
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE TIPO DE AMBIENTE ESTA VACIO");
                    }else{
                        switch (log.getEnvironmentType().toUpperCase()) {
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
                    }


                    //---------------------Faculty-------------------------------------------

                    if (log.getFaculty().trim().length() == 0) {
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE FACULTAD ESTA VACIO");

                    } else {
                        environment.setFaculty(faculty);
                    }

                    if(log.getAvailableResources().trim().length()==0){
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE RECURSOS DISPONIBLES ESTA VACIO");
                    }

                    if(log.getQuantity() == null){
                        errorVacias = true;
                        responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE CANTIDAD DE RECURSOS ESTA VACIO");
                    }


                    if (!errorEnvironment && !errorResources && !errorVacias && !errorTipos && !errorRepetidos) {
                        System.out.println("----------que hay:"+errorEnvironment);
                        responseFile.getLogsSuccess().add("[FILA " + rowNum + "]  LISTA PARA SER REGISTRADA");
                        fileEnvironment.add(log);
                        listEnvironment.add(environment);

                        contSuccess++;

                    } else {
                        System.out.println("--------------------- NO GUARDA AMBIENTE--------------");
                        contError++;
                    }


                }
            }

        }
        enumStatusFile statusFile = enumStatusFile.NO_PROCESS;
        int contSaveRows = 0;
        if (contRows > 0) {
            if (contError > 0) {
                statusFile = enumStatusFile.ERROR;
            } else {
                statusFile = enumStatusFile.SUCCESS;
                if (!fileEnvironment.isEmpty()) {
                    contSaveRows = this.saveFile(fileEnvironment);
                    if (contSaveRows > 0) {
                        statusFile = enumStatusFile.SAVED;
                    } else {
                        statusFile = enumStatusFile.NO_SAVED;
                    }
                }
            }
        }
        responseFile.setStatusFile(statusFile);
        responseFile.setContRows(contRows);
        responseFile.setContErrorRows(contError);
        responseFile.setContSuccessRows(contSuccess);
        responseFile.setContSaveRows(contSaveRows);
            return responseFile;
        }


    private String [] wordFormat(String [] words){
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase().trim();
        }
        return words;
    }


    private int saveFile(List<FileRowEnvironment> fileEnvironment) {

        List<Environment> environments = new ArrayList<>();

        for (FileRowEnvironment log : fileEnvironment) {
            Faculty faculty = facultyRepository.findByFacultyIdIs(log.getFaculty().toUpperCase().trim());
            Environment environment = new Environment(log.getName(),log.getLocation(),log.getCapacity(),faculty);
            switch (log.getEnvironmentType().toUpperCase()) {
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
            environmentRepository.save(environment);

            System.out.println("---------------------GUARDA AMBIENTE--------------");
            //------------------------Resources Avaliable--------------------------------------------

            if (log.getAvailableResources() != null) {

                //Se crea el enviromentResources
                List<Resource> resourcesDb = this.resourceRepository.findAll();
                System.out.println("-----que hay: televisor, videobem" + log.getAvailableResources());
                String[] words = log.getAvailableResources().split(",");
                String[] wordsFormat = wordFormat(words); //formatear palabras

                List<Resource> resources = new ArrayList<>();
                resources = verifyResources(wordsFormat, resourcesDb);

                for (int i = 0; i < resources.size(); i++) {

                    EnvironmentResourceDTO environmentResource = new EnvironmentResourceDTO(
                            log.getQuantity().get(i), environment, resources.get(i));
                    this.iEnvironmentResourceService.saveEnvironmentResource(environmentResource);
                }
            }

            //Boolean inBlock = false;
            //if (environment.getInBlock().equals("SI")) {
            //    inBlock = true;
            //}
            //Program objPrograma = this.programRepository.findByProgramId(materia.getProgramCode());
            //Subject objMateria = new Subject(materia.getSubjectCode(), materia.getName(), materia.getWeeklyOverload(), inBlock, materia.getSemester(), objPrograma, null);
            //materias.add(objMateria);
            environments.add(environment);
        }
        return this.environmentRepository.saveAll(environments).size();
    }

    /*
    *                         environmentRepository.save(environment);
                        System.out.println("---------------------GUARDA AMBIENTE--------------");


                        //------------------------Resources Avaliable--------------------------------------------
                        if (log.getAvailableResources() != null) {

                            //Se crea el enviromentResources
                            List<Resource> resourcesDb = this.resourceRepository.findAll();
                            System.out.println("-----que hay: televisor, videobem" + log.getAvailableResources());
                            String[] words = log.getAvailableResources().split(",");
                            String[] wordsFormat = wordFormat(words); //formatear palabras


                            List<Resource> resources = new ArrayList<>();
                            resources = verifyResources(wordsFormat, resourcesDb);


                            for (int i = 0; i < resources.size(); i++) {

                                EnvironmentResourceDTO environmentResource = new EnvironmentResourceDTO(
                                        log.getQuantity().get(i), environment, resources.get(i));
                                this.iEnvironmentResourceService.saveEnvironmentResource(environmentResource);
                            }
                        }
    * */

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

}

