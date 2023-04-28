package com.pragma.api.controllers;

import com.pragma.api.services.IProgramService;
import com.pragma.api.domain.ProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProgramController {

    private final IProgramService iProgramService;

    @Autowired
    public ProgramController(IProgramService iProgramService) {
        this.iProgramService = iProgramService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {

        return ResponseEntity.status(HttpStatus.OK).body(this.iProgramService.findAllProgram());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getAllPrograms(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.iProgramService.findByProgramId(id));
    }

}
