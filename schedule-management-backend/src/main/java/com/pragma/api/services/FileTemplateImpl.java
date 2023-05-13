package com.pragma.api.services;

import com.pragma.api.domain.Response;
import com.pragma.api.domain.TemplateFileDTO;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
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
    public String processFile() throws IOException {
        String name= "Plantilla_oferta_academica.xlsm";

        final String pathLocal= "C:/Users/Personal/Desktop/ING SISTEMAS/SEMESTRE 8/PROYECTO 1/Proyecto/schedule-management-unicauca/schedule-management-backend/src/main/resources/files/templates/Plantilla_oferta_academica.xlsm";



        String prueba = obtenerRutaArchivo(name);
        prueba.replace("\\","/");

        Workbook workbook = WorkbookFactory.create(new File(prueba));

        Sheet sheet = workbook.getSheetAt(0);


        Row row = sheet.getRow(1);
        System.out.println("dato: "+row.getCell(1));

        workbook.close();

        return null;


    }

    private String obtenerRutaArchivo(String nombreArchivo) {
        try {
            Resource resource = resourceLoader.getResource("file:" + nombreArchivo);
            File file = resource.getFile();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}




