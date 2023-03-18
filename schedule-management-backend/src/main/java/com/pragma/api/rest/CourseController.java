package com.pragma.api.rest;

import com.pragma.api.business.ICourseBusiness;
import com.pragma.api.domain.CourseDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {
    private final ICourseBusiness courseBusiness;

    public CourseController(ICourseBusiness courseBusiness){
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
}
