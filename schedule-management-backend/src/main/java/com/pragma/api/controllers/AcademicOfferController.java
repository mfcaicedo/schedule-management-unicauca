package com.pragma.api.controllers;
import com.pragma.api.services.IFileAcademicOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/academicOffer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AcademicOfferController {

    private final IFileAcademicOffer iFileAcademicOffer;

    @Autowired
    public AcademicOfferController(IFileAcademicOffer iFileAcademicOffer) {
        this.iFileAcademicOffer = iFileAcademicOffer;
    }

    @PostMapping("/uploadFile")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.iFileAcademicOffer.uploadFile(file), HttpStatus.OK);
    }


}
