package com.pragma.api.services;

import com.google.gson.annotations.Since;
import com.pragma.api.domain.*;
import com.pragma.api.model.Program;
import com.pragma.api.model.Subject;
import com.pragma.api.model.TemplateFile;
import com.pragma.api.repository.ITemplateFileRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
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
import java.util.List;

@Service
public class FileTemplateImpl implements ITemplateFileService{


    private ITemplateFileRepository templateFileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private IPersonService iPersonService;

    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private ISubjectService iSubjectService;

    @Autowired
    public FileTemplateImpl(ITemplateFileRepository templateFileRepository) {
        this.templateFileRepository = templateFileRepository;
    }

    @Override
    public TemplateFileDTO uploadTemplateFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] TemplateBytes = outputStream.toByteArray();

        TemplateFile templateFile = new TemplateFile();
        templateFile.setNameFile("Plantilla_Oferta_Academica");
        templateFile.setFile(TemplateBytes);


        return modelMapper.map(this.templateFileRepository.save(templateFile),TemplateFileDTO.class);
    }

    @Override
    public ResponseEntity<Resource> donwloadTemplateFile(String programId) throws IOException {
        String path = getPathTemplate("Plantilla_oferta_academica.xlsx");
        String program = "PIS";
        byte[] temporaryFile;

        //Procesar el archivo de excel
        temporaryFile = Files.readAllBytes(Path.of(path));
        //Workbook workbook = processExcelFile(path,pathBackup, programId);
        Workbook workbook = processExcelFile(path, programId);

        //Ahora se guarda el archivo en un OutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        workbook.write(baos);
        workbook.close();

        // Crear un recurso a partir del contenido del archivo
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

        // Configurar las cabeceras de respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Plantilla_oferta_academica.xlsx");
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

    private Workbook processExcelFile(String path, String programId) throws IOException {

        //TODO 1. consultar los todos los profesores
        List<PersonDTO> teachers = iPersonService.findAllPersonByTypeTeacher();

        //TODO 2. consultar todos las materias pertenecientes a un programa
        //TODO 2.1 consultar el programa
        Program program = modelMapper.map(iProgramService.findByProgramId(programId), Program.class);

        //TODO 2.2 consultar las materias
        List<SubjectDTO> subjects = iSubjectService.findAllByProgram(program);

        subjects.forEach(x-> System.out.println(x.getName()+" "+x.getSemester()));


        //TODO 3. modificar el excel con los datos consultados de profesores y materias
        // Cargar el archivo existente

        Workbook workbook = WorkbookFactory.create(new File(path));

        //obtengo la hoja 2 (Materias)
        Sheet sheetSubjects = workbook.getSheetAt(1);

        //TODO modificar el archivo

        int indiceRow=3;
        int indiceAux=0;
        int semesterCurrent=1;

        while (subjects.size()!=0){
            Row row = sheetSubjects.getRow(indiceRow);

            if(subjects.get(0).getSemester()!=semesterCurrent){
                indiceRow=3;
                indiceAux=0;
                semesterCurrent=subjects.get(0).getSemester();
                continue;
            }

            //estructura que me permite elegir las celdas dependiendo del semestre obtenido
            switch (subjects.get(indiceAux).getSemester()){
                case 1:
                    insertRowGeneric(row,0,subjects,indiceAux);
                    break;
                case 2:
                    insertRowGeneric(row,3,subjects,indiceAux);
                    break;
                case 3:
                    insertRowGeneric(row,6,subjects,indiceAux);
                    break;
                case 4:
                    insertRowGeneric(row,9,subjects,indiceAux);
                    break;
                case 5:
                    insertRowGeneric(row,12,subjects,indiceAux);
                    break;
                case 6:
                    insertRowGeneric(row,15,subjects,indiceAux);
                    break;
                case 7:
                    insertRowGeneric(row,18,subjects,indiceAux);
                    break;
                case 8:
                    insertRowGeneric(row,21,subjects,indiceAux);
                    break;
                case 9:
                    insertRowGeneric(row,24,subjects,indiceAux);
                    break;
                case 10:
                    insertRowGeneric(row,27,subjects,indiceAux);
                    break;
            }

            //Se elimina la materia que ya se almaceno en el excel
            subjects.remove(indiceAux);
            indiceRow++;
        }

        //obtengo la hoja 3 (Profesores)
        Sheet sheetTeachers = workbook.getSheetAt(2);

        //Inserto datos en la hoja 3
        for (int i = 1; i < teachers.size(); i++) {
            Row row = sheetTeachers.getRow(i);

            row.getCell(0).setCellValue(teachers.get(i-1).getPersonCode());
            row.getCell(1).setCellValue(teachers.get(i-1).getFullName());
            row.getCell(2).setCellValue(teachers.get(i-1).getDepartment().getDepartmentName());

        }

        return workbook;
    }

    /**
     * Metodo que retorna la ruta del archivo de plantilla de excel.
     * @param nameFile nombre del archivo de plantilla de excel
     * @return ruta del archivo de plantilla de excel
     */
    private String getPathTemplate(String nameFile) {
        final String pathProjectFileMilthon = "schedule-management-backend/src/main/resources/files/templates/Plantilla_oferta_academica.xlsx";
//        final String pathProjectFileBrandon = "src/main/resources/files/templates/Plantilla_oferta_academica.xlsx";

        try {
            Resource resource = resourceLoader.getResource("file:" + nameFile);
            File file = resource.getFile();
            String absolutePath = file.getAbsolutePath();
            //Cambio e \ por / para que la ruta sea correcta
            absolutePath = absolutePath.replace("\\","/");
            String pathFormat[] = absolutePath.split("/");
            pathFormat[pathFormat.length-1] = "";
            String pathComplete = String.join("/",pathFormat) + pathProjectFileMilthon;
            return pathComplete;
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





    /**
     * Metodo que me permite insertar en las filas de excel en el formato indicado
     * @param row fila actual del excel
     * @param init celda inicial para almacenar los datos correspondientes
     * @param subjects lista de materias
     * @param j variable contador actual
     *
     */
    private void insertRowGeneric(Row row, int init, List<SubjectDTO> subjects, int j){
        row.getCell(init).setCellValue(subjects.get(j).getSubjectCode()+"-"+subjects.get(j).getName());
        row.getCell(init+1).setCellValue(subjects.get(j).getTimeBlock() ? "SI" : "NO");
        row.getCell(init+2).setCellValue(subjects.get(j).getWeeklyOverload());
    }


}




