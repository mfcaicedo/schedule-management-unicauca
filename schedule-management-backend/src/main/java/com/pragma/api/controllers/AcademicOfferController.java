package com.pragma.api.controllers;

import com.pragma.api.domain.Response;
import com.pragma.api.domain.TemplateFileDTO;
import com.pragma.api.services.IFileAcademicOffer;
import com.pragma.api.services.ITemplateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/academicOffer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AcademicOfferController {

    private final IFileAcademicOffer iFileAcademicOffer;

    private final ITemplateFileService iTemplateFileService;

    @Autowired
    public AcademicOfferController(IFileAcademicOffer iFileAcademicOffer, ITemplateFileService iTemplateFileService) {
        this.iFileAcademicOffer = iFileAcademicOffer;
        this.iTemplateFileService = iTemplateFileService;
    }


    @PostMapping("/uploadFile")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.iFileAcademicOffer.uploadFile(file), HttpStatus.OK);
    }

    @PostMapping("/uploadTemplate")
    ResponseEntity<TemplateFileDTO> uploadTemplate(@RequestParam("file") MultipartFile file) throws IOException{
        return new ResponseEntity<>(this.iTemplateFileService.uploadTemplateFile(file),HttpStatus.OK);
    }

    @GetMapping("/downloadTemplate/{programId}")
    ResponseEntity<Resource> downloadTemplate(@PathVariable String programId) throws IOException {
        System.out.println("lleaga al controlador de descarga");
        return this.iTemplateFileService.donwloadTemplateFile(programId);
    }

}
