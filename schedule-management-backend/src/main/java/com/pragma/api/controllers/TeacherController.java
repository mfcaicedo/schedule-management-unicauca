package com.pragma.api.controllers;

import com.pragma.api.services.IFileEnvironmentService;
import com.pragma.api.services.IFileTeachersService;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.services.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    private final ITeacherService iTeacherService;

    private final IFileTeachersService fileTeachersService;

    @Autowired
    public TeacherController(ITeacherService iTeacherService, IFileTeachersService fileTeachersService) {
        this.iTeacherService = iTeacherService;
        this.fileTeachersService = fileTeachersService;
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse> getAllTeacher(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.status(HttpStatus.OK).body(this.iTeacherService.findAllTeacher(pageable));
    }

    @PostMapping("/uploadFile")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.print("SE VA A CREAR");
        return new ResponseEntity<>(this.fileTeachersService.uploadFile(file), HttpStatus.OK);
        //return new ResponseEntity<>(this.fileTeachersService.uploadFile(file), HttpStatus.OK);
    }

}
