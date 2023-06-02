package com.pragma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.api.domain.FacultyDTO;
import com.pragma.api.domain.Response;
import com.pragma.api.services.IFacultyService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FacultyController {
    
    @Autowired
    private IFacultyService facultyService;

    //Consultar facultad mediante id
    @GetMapping("/consultById/{id}")
    public Response<FacultyDTO> consultFacultyById(@PathVariable String id) {
        return this.facultyService.findByFacultyId(id);
    }

    //consultar todas las facultades
    @GetMapping("/consultAllFaculty")
    public Response<List<FacultyDTO>> consultAllFaculty() {
        return this.facultyService.findAllFaculty();
    }
}
