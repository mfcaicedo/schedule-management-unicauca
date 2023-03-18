package com.pragma.api.rest;

import com.pragma.api.business.ITeacherService;
import com.pragma.api.domain.GenericPageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    private final ITeacherService iTeacherService;

    @Autowired

    public TeacherController(ITeacherService iTeacherService) {
        this.iTeacherService = iTeacherService;
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse> getAllTeacher(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.status(HttpStatus.OK).body(this.iTeacherService.findAllTeacher(pageable));
    }
}
