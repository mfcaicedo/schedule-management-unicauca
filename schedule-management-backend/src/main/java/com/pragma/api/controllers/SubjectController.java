package com.pragma.api.controllers;

import com.pragma.api.model.Subject;
import com.pragma.api.services.ISubjectService;
import com.pragma.api.services.IFileSubjectService;
import com.pragma.api.model.Program;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.domain.SubjectDTO;
import com.pragma.api.domain.TemplateSubjectDTO;
import com.pragma.api.services.ITemplateSubjectService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectController {

    private final ISubjectService subjectBusiness;
    private final IFileSubjectService fileSubjectService;
    private final ITemplateSubjectService iTemplateSubjectService;

//    @GetMapping
//    private Response<GenericPageableResponse> findAllByProgramAndSemester(@RequestParam String programId, @RequestParam Integer semester, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
//        return null;
//    }
    @Autowired
    public SubjectController(ISubjectService subjectBusiness, IFileSubjectService fileSubjectService, ITemplateSubjectService iTemplateSubjectService) {
        this.subjectBusiness = subjectBusiness;
        this.fileSubjectService = fileSubjectService;
        this.iTemplateSubjectService = iTemplateSubjectService;
    }

    @PostMapping()
    public Response<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return this.subjectBusiness.createSubject(subjectDTO);
    }

    @PostMapping("/uploadFile")
    ResponseEntity<ResponseFile> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.fileSubjectService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadTemplate/{programId}")
    ResponseEntity<Resource> downloadTemplate(@PathVariable String programId) throws IOException {
        System.out.println("programId: " + programId);
        return this.iTemplateSubjectService.downloadTemplateSubject(programId);
    }

    @GetMapping()
    private Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.subjectBusiness.findAll(pageable);
    }
    @GetMapping("/all")
    private List<Subject> findAllPrueba() {
        return this.subjectBusiness.findAllPrueba();
    }

    @GetMapping("/allSubject")
    public ResponseEntity<GenericPageableResponse> getAllSubject(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        System.out.println("Llega a allSubject " + page + " " + size + " " + sort + " " + order);
        return ResponseEntity.status(HttpStatus.OK).body(this.subjectBusiness.findAllSubject(pageable));
    }

    @GetMapping("/byProgramId")
    public ResponseEntity<GenericPageableResponse> findAllByProgramId(@RequestParam String programId,
                                                                      @RequestParam Integer page,
                                                                      @RequestParam Integer size,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.ok(
                this.subjectBusiness.findAllByProgramId(programId,pageable)
        );
    }

    //Metodo para traer todos los datos semestres asociados a un programa
    @GetMapping("/consultSemestersByProgramId/{id}")
    public Response<List<SubjectDTO>> consultSemestersByProgramId(@PathVariable String id) {
        return this.subjectBusiness.findAllSemesterByProgramId(id);
    }


}
