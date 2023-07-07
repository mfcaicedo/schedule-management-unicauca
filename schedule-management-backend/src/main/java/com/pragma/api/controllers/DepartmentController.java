package com.pragma.api.controllers;

import com.pragma.api.domain.DepartmentDTO;
import com.pragma.api.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(
                departmentService.findAll()
        );
    }

}
