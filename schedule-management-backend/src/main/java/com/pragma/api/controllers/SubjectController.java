package com.pragma.api.controllers;

import com.pragma.api.model.Program;
import com.pragma.api.services.ISubjectBusiness;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectController {

    private final ISubjectBusiness subjectBusiness;
//    @GetMapping
//    private Response<GenericPageableResponse> findAllByProgramAndSemester(@RequestParam String programId, @RequestParam Integer semester, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
//        return null;
//    }
    @Autowired
    public SubjectController(ISubjectBusiness subjectBusiness){
        this.subjectBusiness = subjectBusiness;
    }

    @PostMapping()
    public Response<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return this.subjectBusiness.createSubject(subjectDTO);
    }

    /*
    @GetMapping()
        private Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.subjectBusiness.findAll(pageable);
    }
     */
    @GetMapping
    public ResponseEntity<GenericPageableResponse> getAllSubject(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.status(HttpStatus.OK).body(this.subjectBusiness.findAllSubject(pageable));
    }

    @GetMapping("/byProgramId")
    public ResponseEntity<GenericPageableResponse> findAllByProgramId(@RequestParam String program_id, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.ok(this.subjectBusiness.findAllByProgramId(program_id,pageable));
    }


}
