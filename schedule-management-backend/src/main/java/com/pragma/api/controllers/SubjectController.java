package com.pragma.api.controllers;

import com.pragma.api.services.ISubjectBusiness;
import com.pragma.api.services.IFileSubjectService;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.SubjectDTO;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectController {

    private final ISubjectBusiness subjectBusiness;
    private final IFileSubjectService fileSubjectService;
    
//    @GetMapping
//    private Response<GenericPageableResponse> findAllByProgramAndSemester(@RequestParam String programId, @RequestParam Integer semester, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
//        return null;
//    }
    @Autowired
    public SubjectController(ISubjectBusiness subjectBusiness, IFileSubjectService fileSubjectService){
        this.subjectBusiness = subjectBusiness;
        this.fileSubjectService = fileSubjectService;
    }

    @PostMapping()
    public Response<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return this.subjectBusiness.createSubject(subjectDTO);
    }
    @PostMapping("/uploadFile")
    ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.fileSubjectService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping()
    private Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.subjectBusiness.findAll(pageable);
    }

}
