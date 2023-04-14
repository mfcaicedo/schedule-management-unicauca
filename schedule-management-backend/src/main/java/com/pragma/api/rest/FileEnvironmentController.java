package com.pragma.api.rest;

import com.pragma.api.business.IFileEnvironmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fileEnvironment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileEnvironmentController {


    private final IFileEnvironmentService fileEnvironmentService;

    @Autowired
    public FileEnvironmentController(IFileEnvironmentService fileEnvironmentService) {
        this.fileEnvironmentService = fileEnvironmentService;
    }

    @PostMapping("/uploadEnvironment")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.fileEnvironmentService.uploadFile(file), HttpStatus.OK);
    }


}
