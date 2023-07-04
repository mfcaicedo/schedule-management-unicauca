package com.pragma.api.services;

import com.pragma.api.domain.EnvironmentResourceDTO;
import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.model.enums.StatusFileEnumeration;
import com.pragma.api.model.*;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.repository.IEnvironmentRepository;
import com.pragma.api.repository.IFacultyRepository;
import com.pragma.api.repository.IResourceRepository;
import com.pragma.api.util.file.FileEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
//import org.hibernate.mapping.Set;
import com.pragma.api.util.file.templateclasses.FileRowSubject;
//import jdk.javadoc.internal.doclint.Env;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class FileEnvironmentImpl implements IFileEnvironmentService {

    private IEnvironmentRepository environmentRepository;
    @Autowired
    private IEnvironmentResourceService iEnvironmentResourceService;

    @Autowired
    private ResourceLoader resourceLoader;

    private IFacultyRepository facultyRepository;

    private IResourceRepository resourceRepository;
    @Autowired
    private IFacultyService iFacultyService;

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
        ResponseFile responseFile = new ResponseFile();
        List<FileRowEnvironment> logs = fileEnvironment.getLogs(file, responseFile);
        return processFile(logs);

    }


         private Workbook processExcelFile(String path) throws IOException {

        //TODO 1. consultar todos las facultades y recursos
        List <Faculty> faculties = facultyRepository.findAll();
        List <Resource> resources = resourceRepository.findAll();
        //TODO 3. modificar el excel con los datos consultados de facultades y recursos
        // Cargar el archivo existente

        Workbook workbook = WorkbookFactory.create(new File(path));

        //TODO modificar el archivo

        //obtengo la hoja 1 y 3 (Facultades)
             Sheet sheetResources = workbook.getSheetAt(0);
             Sheet sheetFaculties = workbook.getSheetAt(2);

             for (int i = 1; i <= resources.size(); i++) {
                 Row row = sheetResources.getRow(i);
                     row.getCell(9).setCellValue(resources.get(i-1).getName());
             }

        //Inserto datos en la hoja 3
        for (int i = 1; i <= faculties.size(); i++) {
            Row row = sheetFaculties.getRow(i);

            row.getCell(0).setCellValue(faculties.get(i-1).getFacultyId());
            row.getCell(1).setCellValue(faculties.get(i-1).getFacultyName());
        }

        return workbook;
    }


    @Override
    public ResponseEntity<org.springframework.core.io.Resource> downloadTemplateFile() throws IOException {
         // Obtener la ruta del archivo de plantilla
        List<String> listPath = getPathTemplate("Plantilla_Ambientes.xlsx");
        String path = listPath.get(0);
        // Definir una variable para el archivo temporal
        byte[] temporaryFile;
        // Leer todo el contenido del archivo y almacenarlo en la variable temporaryFile
        try {
            temporaryFile = Files.readAllBytes(Path.of(listPath.get(0)));
        }catch (IOException e){
            temporaryFile = Files.readAllBytes(Path.of(listPath.get(1)));
            path = listPath.get(1);
        }


    // Procesar el archivo Excel utilizando un método llamado processExcelFile y obtener el objeto Workbook
        temporaryFile = Files.readAllBytes(Path.of(path));
        Workbook workbook = processExcelFile(path);

    // Crear un OutputStream para guardar el archivo Excel en memoria
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // Escribir el contenido del libro (workbook) en el OutputStream (baos)
    workbook.write(baos);
    workbook.close();
    // Crear un recurso a partir del contenido del archivo almacenado en el OutputStream
    ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());
    // Configurar las cabeceras de respuesta HTTP
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=Plantilla_Ambientes.xlsx");
    headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    // Cerrar el libro y el OutputStream
    workbook.close();
    baos.close();

    // Restaurar el archivo original utilizando el método restoreFileBytes
    //restoreFileBytes(temporaryFile, path);

    // Devolver el archivo como respuesta HTTP con el recurso y las cabeceras configuradas
    return ResponseEntity.ok()
            .headers(headers)
            .body(resource);

    }

    private List<String> getPathTemplate(String nameFile) {
        final String pathProjectFileMain = "schedule-management-backend/src/main/resources/files/templates/Plantilla_Ambientes.xlsx";
        final String pathProjectFileAux = "src/main/resources/files/templates/Plantilla_Ambientes.xlsx";

        try {
            // Obtener el recurso del archivo utilizando resourceLoader y agregando "file:" al nombre del archivo
            org.springframework.core.io.Resource resource = resourceLoader.getResource("file:" + nameFile);

            // Obtener el archivo a partir del recurso
            File file = resource.getFile();

            // Obtener la ruta absoluta del archivo
            String absolutePath = file.getAbsolutePath();
            // Reemplazar todas las ocurrencias de "\" por "/" en la ruta absoluta para que sea compatible con el sistema de archivos
            absolutePath = absolutePath.replace("\\","/");
            // Dividir la ruta absoluta en un arreglo utilizando "/" como separador
            String pathFormat[] = absolutePath.split("/");
            // Reemplazar el último elemento del arreglo por una cadena vacía para eliminar el nombre del archivo
            pathFormat[pathFormat.length-1] = "";
            // Unir los elementos del arreglo nuevamente en una cadena utilizando "/" como separador y agregar el path del proyecto
            //String pathComplete = String.join("/", pathFormat) + pathProjectFile;
            String pathCompleteMain = String.join("/",pathFormat) + pathProjectFileMain;
            String pathCompleteAux = String.join("/",pathFormat) + pathProjectFileAux;
            // Devolver la ruta completa
            List<String> listPathComplete = new ArrayList<>();
            listPathComplete.add(pathCompleteMain);
            listPathComplete.add(pathCompleteAux);
            return listPathComplete;
        } catch (Exception e) {
            // Imprimir la traza de la excepción en caso de error
            e.printStackTrace();
            return null;
        }
    }
    //PROCESS FILE
    @Override
    public ResponseFile processFile(List<FileRowEnvironment> logs) {

        ResponseFile responseFile = new ResponseFile();
        StatusFileEnumeration statusFile = StatusFileEnumeration.NO_PROCESS;
        //----------------------------------------------------------------------
        List<String> environmentNames = new ArrayList<>();
        List<FileRowEnvironment> fileEnvironment = new ArrayList<>();
        List<Environment> listEnvironment = new ArrayList<>();
        List<String> environmentLocation = new ArrayList<>();

        //----------------------------------------------------------------------

        //List<String> infoLogs = newf ArrayList<>();
        int contRows = 0;
        int contSuccess = 0;
        int contError = 0;
        boolean errorTipos = false;
        if(logs.isEmpty()){
            statusFile = StatusFileEnumeration.ERROR;
            responseFile.getLogsGeneric().add("No hay datos");
            errorTipos = true;
        }else{
            for (FileRowEnvironment log : logs) {

                Faculty faculty = facultyRepository.findByFacultyIdIs(log.getFaculty().toUpperCase().trim());
                Environment environment = new Environment();

                if (faculty == null) {
                    statusFile = StatusFileEnumeration.ERROR;
                    errorTipos = true;
                    responseFile.getLogsGeneric().add("[FILA "+ (log.getRowNum()+1) + "] LA FACULTAD ESTA VACIA");
                }else {
                    int rowNum = log.getRowNum();
                    if (rowNum != -1) {
                        contRows++;
                        rowNum = rowNum + 1;
                        boolean errorEnvironment = false;
                        boolean errorResources = false;
                        boolean errorVacias = false;

                        boolean errorRepetidos = false;

                        //---------------------------Name-----------------------------------
                        if (log.getName().trim().length() == 0) {
                            errorVacias = true;
                            responseFile.getLogsEmptyFields().add("[FILA " + rowNum + "]  EL NOMBRE DEL AMBIENTE ESTA VACIO");
                        }else{
                            Environment environmentaux = new Environment();
                            environmentaux.setName(log.getName());
                            environmentaux.setLocation(log.getLocation());

                            if(this.existsInList(logs, environmentaux)){
                                responseFile.getLogsGeneric().add("[FILA " + rowNum + "]  EL NOMBRE DEL AMBIENTE ESTA REPETIDO: " + log.getName());
                                errorRepetidos = true;

                            }else{
                                //Validar que el nombre del ambiente no exista en la base de datos
                                List<Environment> enviromentsDb = this.environmentRepository.findAll();
                                if (this.existsInBD(enviromentsDb, environmentaux)) {
                                    responseFile.getLogsGeneric().add("[FILA " + rowNum + "]  EL AMBIENTE INDICADO YA EXISTE EN LA BASE DE DATOS: " + log.getName());
                                    errorRepetidos = true;
                                }
                            }
                        }

                        //-------------------------------Location-------------------------------
                        if(!this.locationInList(logs, log.getLocation().toUpperCase())&&!log.getLocation().toUpperCase().equals("NO APLICA")){
                            errorTipos = true;
                            responseFile.getLogsGeneric().add("[FILA" + rowNum + "] DIGITE PRIMERO LA UBICACION DEL AMBIENTE, ESTA UBICACION NO EXISTE");
                        }
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

                        } else {
                            environment.setCapacity(log.getCapacity());
                        }
                        //--------------------------Type-----------------------------------------

                        if (log.getEnvironmentType().trim().length() == 0) {
                            errorVacias = true;
                            responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE TIPO DE AMBIENTE ESTA VACIO");
                        }

                        //---------------------Faculty-------------------------------------------

                        if (log.getFaculty().trim().length() == 0) {
                            errorVacias = true;
                            responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE FACULTAD ESTA MAL DIGITADO");

                        }

                        if(log.getAvailableResources().trim().length()==0){
                            errorVacias = true;
                            responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE RECURSOS DISPONIBLES ESTA VACIO");
                        }

                        if(log.getQuantity() == null){
                            errorVacias = true;
                            responseFile.getLogsEmptyFields().add("FILA" + rowNum + "] EL CAMPO DE CANTIDAD DE RECURSOS ESTA MAL DIGITADO");
                        }
                        if (!errorEnvironment && !errorResources && !errorVacias && !errorTipos && !errorRepetidos) {
                            System.out.println("----------que hay:"+errorEnvironment);
                            responseFile.getLogsSuccess().add("[FILA " + rowNum + "]  LISTA PARA SER REGISTRADA");
                            fileEnvironment.add(log);
                            //listEnvironment.add(environment);
                            contSuccess++;

                        } else {
                            System.out.println("--------------------- NO GUARDA AMBIENTE--------------");
                            contError++;
                        }
                    }
                }

            }
        }

        int contSaveRows = 0;
        if (contRows > 0) {
            if (contError > 0) {
                statusFile = StatusFileEnumeration.ERROR;
            } else {
                statusFile = StatusFileEnumeration.SUCCESS;
                if (!fileEnvironment.isEmpty()) {
                    contSaveRows = this.saveFile(fileEnvironment);
                    if (contSaveRows > 0) {
                        statusFile = StatusFileEnumeration.SAVED;
                    } else {
                        statusFile = StatusFileEnumeration.NO_SAVED;
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

    /**
     * Verificar que el usuario ha ingresado antes un edificio de ubicación
     * @param logs
     * @param locationaux
     * @return
     */
    private boolean locationInList(List<FileRowEnvironment> logs, String locationaux) {
        boolean encontrado = false;
        int cont = 0;
        for (FileRowEnvironment elementoEnvironment : logs) {
            if (elementoEnvironment.getName().toUpperCase().equals(locationaux)) {
                encontrado= true;
            }
        }
        return encontrado;
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

            environments.add(environment);

            if (log.getLocation().toUpperCase().equals("NO APLICA")) {
                environment.setParentEnvironment(null);
            } else {
                List<Environment> enviromentsDb = this.environmentRepository.findAll();
                environment.setParentEnvironment(selectParent(log.getLocation().toString(), enviromentsDb));
            }
        }
        return this.environmentRepository.saveAll(environments).size();
    }

    private List<Resource> verifyResources(String[] wordsFormat, List<Resource> resourcesDb){
        List<Resource> resources = new ArrayList<>();
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

    private Environment selectParent(String ubicacion, List<Environment> environmentsDb) {
        Environment environmentP = null;
        for (int i = 0; i < environmentsDb.size(); i++) {
            if(environmentsDb.get(i).getName().equals(ubicacion)){
                environmentP = environmentsDb.get(i);
            }
        }
        return environmentP;
    }

    private boolean existsInList(List<FileRowEnvironment> logs, Environment environmentaux) {
        boolean encontrado = false;
        int cont = 0;
        for (FileRowEnvironment elementoEnvironment : logs) {
            if (elementoEnvironment.getName().equals(environmentaux.getName()) && elementoEnvironment.getLocation().equals(environmentaux.getLocation())) {
                cont++;
            }
            if(cont>1){
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    private boolean existsInBD(List<Environment> logs, Environment environmentaux) {
        boolean encontrado = false;
        int cont = 0;
        for (Environment elementoEnvironment : logs) {

            if (elementoEnvironment.getName().equals(environmentaux.getName()) && elementoEnvironment.getLocation().equals(environmentaux.getLocation())) {
                System.out.println("Nombre Ambiente: " + elementoEnvironment.getName());
                System.out.println("Nombre ambiente: " + environmentaux.getName());
                System.out.println("Location: " + elementoEnvironment.getLocation());
                System.out.println("Location aux Ambiente: " + environmentaux.getLocation());
                encontrado = true;
                break;
            }


        }
        return encontrado;
    }



}



