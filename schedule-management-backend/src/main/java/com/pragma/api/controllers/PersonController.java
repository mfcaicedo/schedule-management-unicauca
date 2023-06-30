package com.pragma.api.controllers;

import com.pragma.api.domain.Response;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.model.Person;
import com.pragma.api.repository.IPersonRepository;
import com.pragma.api.services.IPersonService;
import com.pragma.api.services.IFileTeachersService;
import com.pragma.api.domain.GenericPageableResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {

    private final IPersonService iPersonService;

    private final IPersonRepository iPersonRepository;

    private final IFileTeachersService fileTeachersService;

    public PersonController(IPersonService iPersonService, IPersonRepository iPersonRepository, IFileTeachersService fileTeachersService) {
        this.iPersonService = iPersonService;
        this.iPersonRepository = iPersonRepository;
        this.fileTeachersService = fileTeachersService;
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse> getAllPerson(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.status(HttpStatus.OK).body(this.iPersonService.findAllPerson(pageable));
    }

    @PreAuthorize("hasRole('ROLE_ACADEMIC_MANAGER')")
    @PostMapping("/uploadFile")
    ResponseEntity<ResponseFile> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    //ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.print("SE VA A CREAR");
        return new ResponseEntity<>(this.fileTeachersService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadTeacherTemplate")
    ResponseEntity<Resource> downloadTeacherTemplate() throws IOException {
        System.out.println("lleaga al controlador de descarga de plantilla profesores");
        return this.fileTeachersService.donwloadTeacherTemplateFile();
    }

    @GetMapping("/byPersonType")
    public Response<GenericPageableResponse> findAllByPersonType(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @RequestParam String personType){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.iPersonService.findAllByPersonType(pageable, personType);
    }

    @GetMapping("/byDepartmetId")
    public Response<GenericPageableResponse> findAllByDepartmetName(@RequestParam Integer page,
                                                                    @RequestParam Integer size,
                                                                    @RequestParam String sort,
                                                                    @RequestParam String order,
                                                                    @RequestParam String departmentId,
                                                                    @RequestParam String personType
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.iPersonService.findAllByDepartmentName(pageable, departmentId, personType);

    }

    @GetMapping("/all")
    public List<Person> getAllPersonss() {
        return iPersonRepository.findAll();
    }

    @GetMapping("/nameByCode/{personCode}")
    public Response<String> findNameByPersonCode(@PathVariable String personCode){
        return this.iPersonService.findNameByPersonCode(personCode);
    }
}
