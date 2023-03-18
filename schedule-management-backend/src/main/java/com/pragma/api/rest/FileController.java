package com.pragma.api.rest;

import com.pragma.api.business.IFileBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {

    private final IFileBusiness iFileBusiness;

    @Autowired
    public FileController(IFileBusiness iFileBusiness) {
        this.iFileBusiness = iFileBusiness;
    }

    @PostMapping("/upload")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.iFileBusiness.uploadFile(file), HttpStatus.OK);
    }


}
