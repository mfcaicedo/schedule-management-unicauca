package com.pragma.api.services;

import com.pragma.api.domain.*;
import com.pragma.api.model.Program;
import com.pragma.api.model.Subject;
import com.pragma.api.model.TemplateFile;
import com.pragma.api.repository.ITemplateFileRepository;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        String path = getPathTemplate("Plantilla_oferta_academica.xlsm");
        String program = "PIS";

        //Procesar el archivo de excel
        Workbook workbook = processExcelFile(path, programId);

        //Ahora se guarda el archivo en un OutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);

        // Crear un recurso a partir del contenido del archivo
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

        // Configurar las cabeceras de respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Plantilla_oferta_academica_" + programId + ".xlsm");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        //Cierro el libro
        workbook.close();

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

        //TODO 3. modificar el excel con los datos consultados de profesores y materias
        Workbook workbook = WorkbookFactory.create(new File(path));
        //obtengo la hoja 1 (PRINCIPAL)
        Sheet sheet = workbook.getSheetAt(0);
        //obtengo la fila 1
        Row row = sheet.getRow(1);
        System.out.println("dato: "+row.getCell(1));

        //TODO modificar el archivo
        Cell cell = row.getCell(1);
        cell.setCellValue("Para donde las niñas");
        return workbook;
    }

    /**
     * Metodo que retorna la ruta del archivo de plantilla de excel.
     * @param nameFile nombre del archivo de plantilla de excel
     * @return ruta del archivo de plantilla de excel
     */
    private String getPathTemplate(String nameFile) {
        final String pathProjectFile = "schedule-management-backend/src/main/resources/files/templates/Plantilla_oferta_academica.xlsm";
        try {
            Resource resource = resourceLoader.getResource("file:" + nameFile);
            File file = resource.getFile();
            String absolutePath = file.getAbsolutePath();
            //Cambio e \ por / para que la ruta sea correcta
            absolutePath = absolutePath.replace("\\","/");
            String pathFormat[] = absolutePath.split("/");
            pathFormat[pathFormat.length-1] = "";
            String pathComplete = String.join("/",pathFormat) + pathProjectFile;
            return pathComplete;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}




