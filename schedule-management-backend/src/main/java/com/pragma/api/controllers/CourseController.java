package com.pragma.api.controllers;

import com.pragma.api.services.ICourseService;
import com.pragma.api.domain.CourseDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {
    private final ICourseService courseBusiness;

    public CourseController(ICourseService courseBusiness){
        this.courseBusiness = courseBusiness;
    }

    @PostMapping()
    public Response<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        return this.courseBusiness.createCourse(courseDTO);
    }
    @GetMapping("/available")
    private ResponseEntity<GenericPageableResponse> findAllAvailable(@RequestParam String programId, @RequestParam Integer semester, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.ok(this.courseBusiness.findAllByAvailable(programId, semester,pageable));
    }

    @GetMapping("/byProgramSemester")
    private ResponseEntity<GenericPageableResponse> findAllByProgramSemester(@RequestParam String programId, @RequestParam Integer semester, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.ok(this.courseBusiness.findAllBySubjectProgramAndSemester(programId, semester,pageable));
    }



    @GetMapping
    private Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.courseBusiness.findAll(pageable);
    }

    @GetMapping("/{id}")
    private CourseDTO findById(@PathVariable Integer id){
        System.out.println("llego al controlado con el id" + id);
        return this.courseBusiness.findById(id);
    }

}
