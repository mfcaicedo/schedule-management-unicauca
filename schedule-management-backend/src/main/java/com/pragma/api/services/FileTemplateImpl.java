package com.pragma.api.services;

import com.pragma.api.domain.TemplateFileDTO;
import com.pragma.api.model.Subject;
import com.pragma.api.model.TemplateFile;
import com.pragma.api.repository.ITemplateFileRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileTemplateImpl implements ITemplateFileService{


    private ITemplateFileRepository templateFileRepository;

    @Autowired
    private ModelMapper modelMapper;

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
}
