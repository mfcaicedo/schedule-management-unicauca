package com.pragma.api.services;

import com.pragma.api.domain.PersonDTO;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.model.enums.StatusFileEnumeration;
import com.pragma.api.domain.*;
import com.pragma.api.model.Department;
import com.pragma.api.model.Person;
import com.pragma.api.model.enums.PersonTypeEnumeration;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.util.file.FileTeachers;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileTeachersImpl implements IFileTeachersService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPersonService iPersonService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private  IDepartmentService departmentService;

    @Override
    public ResponseFile uploadFile(MultipartFile file) throws IOException {
        FileTeachers fileTeachers = new FileTeachers();
        ResponseFile responseFile = new ResponseFile();
        List<FileRowTeacher> logs = fileTeachers.getLogs(file, responseFile);

        if (logs.size() == 0) {
            return responseFile;
        }else {
            return processFile(logs, responseFile);
        }
    }

    @Override
    public ResponseFile processFile(List<FileRowTeacher> logs, ResponseFile responseFile) {
        List<String> infoLogs = new ArrayList<>();
        List<String> infoErrores = new ArrayList<>();
        List<String> infoLogsVacias = new ArrayList<>();
        List<String> infoErroresVacias = new ArrayList<>();
        List<String> infoErroresTipos = new ArrayList<>();
        List<String> infoSuccess = new ArrayList<>();
        List<FileRowTeacher> archivoProfesores = new ArrayList<>();

        int contRows = 0;
        int contSuccess = 0;
        int contError = 0;

        for(FileRowTeacher log:logs){

            int rowNum = log.getRowNum() + 1;

            if (rowNum != -1) {
                contRows++;
                rowNum = rowNum + 1;

                Boolean errorProgram = false;
                Boolean errorVacias = false;

                Boolean errorDepartamento = false;
                boolean errorTipos = false;
                boolean errorRepetidos = false;

                System.out.println("Nombre profesor: " + log.getName_teacher());
                System.out.println("Codigo profesor: " + log.getCode_teacher());
                System.out.println(log.getName_department());

                //Validacion nombre vacio
                if(log.getName_teacher().trim().length() == 0){
                    infoErroresVacias.add("[FILA " + (rowNum-1) + "] EL NOMBRE DEL PROFESOR ESTA VACIO (NOMBRE OBLIGATORIO)");
                    errorVacias = true;
                }

                System.out.println("este es el tipo de variable: " );
                //validacion de codigo de profesor
                //if(log.getCode_teacher().equals(null)){
                //if(log.getCode_teacher() == 0){
                if(log.getCode_teacher().equals(0)){
                    infoErroresVacias.add("[FILA " + (rowNum-1) + "] EL CODIGO DEL PROFESOR ESTA VACIO (CODIGO OBLIGATORIO)");
                    errorVacias = true;
                }

                System.out.println("mirar este: " + log.getName_department());
                if(log.getName_department().trim().length() == 0){
                    infoErroresVacias.add("[FILA " + (rowNum-1) + "] EL DEPARTAMENTO DEL PROFESOR ESTA VACIO (DEPARTAMENTO OBLIGATORIO)");
                    errorVacias = true;
                }

                //si alguna fila esta con error no realiza el proceso de guardado
                if(errorVacias == false){
                    //buscamos si existe la persona ya creada
                    PersonDTO personDTO = this.iPersonService.findByCode(String.valueOf(log.getCode_teacher()));

                    //verificamos que el profesor no se encuentre registrado
                    if(personDTO == null){

                        //buscamos el id del departamento
                        Department department =  departmentService.findDepartmentByDepartmentName(log.getName_department().trim());

                        if(department != null){
                            Person person = new Person();
                            person.setPersonCode(log.getCode_teacher().toString());
                            person.setFullName(log.getName_teacher());
                            person.setDepartment(department);
                            person.setPersonType(PersonTypeEnumeration.TEACHER);
                            personRepository.save(person);
                            infoLogs.add("Teacher Created succesfully!");
                        }else{
                            errorDepartamento = true;
                            infoErrores.add("[FILA " + (rowNum-1) + "] EL DEPARTAMENTO ASIGNADO AL PROFESOR NO SE ENCUENTRA REGISTRADO: " + log.getName_department().trim());
                            infoLogs.add("Teacher NOT Created");
                        }

                        if (!errorDepartamento) {
                            infoLogs.add("[FILA " + (rowNum-1) + "] LISTA PARA SER REGISTRADA");
                            contSuccess++;
                        } else {
                            infoLogs.add("[FILA " + (rowNum-1) + "] CONTIENE ERRORES:");
                            for (String infoError : infoErrores) {
                                infoLogs.add(infoError);
                            }
                            contError++;
                        }

                    }
                }

                if (!errorDepartamento && !errorProgram && !errorVacias && !errorTipos && !errorRepetidos) {
                    infoSuccess.add("[FILA " + (rowNum-1) + "]  LISTA PARA SER REGISTRADA");
                    archivoProfesores.add(log);
                    contSuccess++;
                } else {
                    contError++;
                }
            }
        }

        StatusFileEnumeration statusFile = StatusFileEnumeration.NO_PROCESS;
        int contSaveRows = 0;

        if (contRows > 0) {
            if (contError > 0) {
                //infoLogs.add(0, "ESTADO ARCHIVO: Error");
                statusFile = StatusFileEnumeration.ERROR;
            } else {
                //infoLogs.add(0, "ESTADO ARCHIVO: Success");
                statusFile = StatusFileEnumeration.SUCCESS;
            }
        }

        responseFile.setStatusFile(statusFile);
        responseFile.setContRows(contRows);
        responseFile.setContErrorRows(contError);
        responseFile.setContSuccessRows(contSuccess);
        responseFile.setContSaveRows(contSaveRows);
        responseFile.setLogsType(infoErroresTipos);
        responseFile.setLogsEmptyFields(infoErroresVacias);
        responseFile.setLogsGeneric(infoErrores);
        responseFile.setLogsSuccess(infoSuccess);
        return responseFile;
    }

    @Override
    public ResponseEntity<Resource> donwloadTeacherTemplateFile() throws IOException {
        List<String> listPath = getPathTemplate("Plantilla_profesores.xlsx");
        String path = listPath.get(0);
        byte[] temporaryFile;

        //Procesar el archivo de excel
        try {
            temporaryFile = Files.readAllBytes(Path.of(listPath.get(0)));
        }catch (IOException e){
            temporaryFile = Files.readAllBytes(Path.of(listPath.get(1)));
            path = listPath.get(1);
        }
        Workbook workbook = processExcelFile(path);

        //Ahora se guarda el archivo en un OutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        workbook.write(baos);
        workbook.close();

        // Crear un recurso a partir del contenido del archivo
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

        // Configurar las cabeceras de respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Plantilla_profesores.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        //Cierro el libro
        workbook.close();
        baos.close();

        //restoreFile(path,pathBackup);
        restoreFileBytes(temporaryFile,path);
        // Devolver el archivo como respuesta
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


    private List<String> getPathTemplate(String nameFile) {
        //final String pathProjectFileMilthon = "src/main/resources/files/templates/Plantilla_profesores.xlsx";
        final String pathProjectFileMain = "schedule-management-backend/src/main/resources/files/templates/Plantilla_profesores.xlsx";
        final String pathProjectFileAux = "src/main/resources/files/templates/Plantilla_profesores.xlsx";
        try {
            Resource resource = resourceLoader.getResource("file:" + nameFile);
            File file = resource.getFile();
            String absolutePath = file.getAbsolutePath();
            //Cambio e \ por / para que la ruta sea correcta
            absolutePath = absolutePath.replace("\\","/");
            String pathFormat[] = absolutePath.split("/");
            pathFormat[pathFormat.length-1] = "";
            //String pathComplete = String.join("/",pathFormat) + pathProjectFileMilthon;
            String pathCompleteMain = String.join("/",pathFormat) + pathProjectFileMain;
            String pathCompleteAux = String.join("/",pathFormat) + pathProjectFileAux;

            List<String> listPathComplete = new ArrayList<>();
            listPathComplete.add(pathCompleteMain);
            listPathComplete.add(pathCompleteAux);
            return listPathComplete;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que me permite restaurar la plantilla
     * @param temporary copia temporal de plantilla en RAM
     * @param path ruta de la plantilla existente
     *
     */

    private void restoreFileBytes(byte[] temporary, String path) throws IOException {
        if (temporary != null) {
            // Restaura el archivo original desde la copia temporal en memoria RAM
            Files.write(Path.of(path), temporary);

            // Limpia la copia temporal en memoria RAM
            temporary = null;
        }
    }


    private Workbook processExcelFile(String path) throws IOException {

        //consultamos todos los departamentos
        List<DepartmentDTO> departments = departmentService.findAll();
        System.out.println("xxxxxxxxxxxxxxxxx");
        departments.forEach(x-> System.out.println(x.getDepartmentName()));


        // Cargar el archivo existente
        Workbook workbook = WorkbookFactory.create(new File(path));


        //obtengo la hoja 2 (DEPARTAMENTOS)
        Sheet sheetSubjects = workbook.getSheetAt(1);

        //MODIFICAMOS EL ARCHIVO EXCEL
        for (int i = 1; i <= departments.size(); i++) {
            System.out.println("lega");
            Row row = sheetSubjects.getRow(i);

            row.getCell(0).setCellValue(departments.get(i-1).getDepartmentId());
            row.getCell(1).setCellValue(departments.get(i-1).getDepartmentName());
        }


        return workbook;
    }

}



