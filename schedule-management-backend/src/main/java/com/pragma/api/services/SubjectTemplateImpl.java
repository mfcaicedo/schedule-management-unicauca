package com.pragma.api.services;

import com.google.gson.annotations.Since;
import com.pragma.api.domain.*;
import com.pragma.api.model.Program;
import com.pragma.api.model.Subject;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectTemplateImpl implements ITemplateSubjectService {

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
    public SubjectTemplateImpl() {
    }

    @Override
    public ResponseEntity<Resource> downloadTemplateSubject(String programId) throws IOException {
        List<String> listPath = getPathTemplate("Plantilla_Materias.xlsx");
        String path = listPath.get(0);
        byte[] temporaryFile;

        //Procesar el archivo de excel
        try {
            temporaryFile = Files.readAllBytes(Path.of(path));
        }catch (IOException e){
            temporaryFile = Files.readAllBytes(Path.of(listPath.get(1)));
            path = listPath.get(1);
        }
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
        headers.add("Content-Disposition", "attachment; filename=Plantilla_Materias.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        //Cierro el libro
        workbook.close();
        baos.close();

        //restoreFile(path,pathBackup);
        restoreFileBytes(temporaryFile, path);
        // Devolver el archivo como respuesta
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    private Workbook processExcelFile(String path, String programId) throws IOException {
        // Obtener el programa por medio del programID
        System.out.println("ProgramID: " + programId);
        ProgramDTO program1 = iProgramService.findByProgramId(programId);
        System.out.println("Program veerr: " + program1.getName());
        System.out.println("Program veerr id: " + program1.getProgramId());
        Program program = modelMapper.map(iProgramService.findByProgramId(programId), Program.class);
        System.out.println("Program: " + program.getName());
        System.out.println("Program: " + program.getProgramId());


        // Cargar plantilla de Excel
        Workbook workbook = WorkbookFactory.create(new File(path));

        // Obtener la hoja principal (hoja 1)
        Sheet sheetSubjects = workbook.getSheetAt(0);

        // Se obtiene la fila 2 (en esta fila se encuentra el NOMBRE del programa)
        Row row = sheetSubjects.getRow(1);
        // Se obtiene la celda 2 y se escribe el nombre del programa en dicha celda
        row.getCell(1).setCellValue(program.getName());

        // Se obtiene la fila 3 (en esta fila se encuentra el CODIGO del programa)
        Row row_1 = sheetSubjects.getRow(2);
        // Se obtiene la celda 2 y se escribe el codigo del programa en dicha celda
        row_1.getCell(1).setCellValue(program.getProgramId());

        return workbook;
    }

    /**
     * Metodo que retorna la ruta del archivo de plantilla de excel.
     *
     * @param nameFile nombre del archivo de plantilla de excel
     * @return ruta del archivo de plantilla de excel
     */
    private List<String> getPathTemplate(String nameFile) {
        final String pathProjectFileMain = "schedule-management-backend/src/main/resources/files/templates/Plantilla_Materias.xlsx";
        final String pathProjectFileAux = "src/main/resources/files/templates/Plantilla_Materias.xlsx";
        try {
            Resource resource = resourceLoader.getResource("file:" + nameFile);
            File file = resource.getFile();
            String absolutePath = file.getAbsolutePath();
            //Cambio e \ por / para que la ruta sea correcta
            absolutePath = absolutePath.replace("\\", "/");
            String pathFormat[] = absolutePath.split("/");
            pathFormat[pathFormat.length - 1] = "";
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
     *
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
     * Metodo que me permite insertar en las filas de excel en el formato
     * indicado
     *
     * @param row fila actual del excel
     * @param init celda inicial para almacenar los datos correspondientes
     * @param subjects lista de materias
     * @param j variable contador actual
     *
     */
    private void insertRowGeneric(Row row, int init, List<SubjectDTO> subjects, int j) {
        row.getCell(init).setCellValue(subjects.get(j).getSubjectCode() + "-" + subjects.get(j).getName());
        row.getCell(init + 1).setCellValue(subjects.get(j).getTimeBlock() ? "SI" : "NO");
        row.getCell(init + 2).setCellValue(subjects.get(j).getWeeklyOverload());
    }

}
