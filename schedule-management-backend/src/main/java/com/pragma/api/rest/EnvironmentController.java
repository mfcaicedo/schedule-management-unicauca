package com.pragma.api.rest;

import com.pragma.api.business.IEnvironmentService;
import com.pragma.api.domain.EnvironmentDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.ResourceList;
import com.pragma.api.domain.Response;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/environment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnvironmentController {

    private final IEnvironmentService environmentService;

    public EnvironmentController(IEnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping()
    public Response<EnvironmentDTO> createEnvironment(@Valid @RequestBody EnvironmentDTO environmentDTO) {
        System.out.println(environmentDTO);
        return this.environmentService.createEnvironment(environmentDTO);
    }

    @PostMapping("/addResource")
    public Response<Boolean> addResourceToEnvironment(@RequestBody ResourceList resourceList, @RequestParam Integer environmentId) {
        return this.environmentService.addResourceToEnvironment(resourceList, environmentId);
    }

    @PutMapping("/updateResource")
    public Response<Boolean> updateResourceToEnvironment(@RequestBody ResourceList resourceList, @RequestParam Integer environmentId) {
        return this.environmentService.updateResourceToEnvironment(resourceList, environmentId);
    }

    @GetMapping()
    public Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.environmentService.findAll(pageable);
    }

    @GetMapping("/byResource")
    public Response<GenericPageableResponse> findAllByResourceId(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @RequestParam Integer resourceId){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.environmentService.findAllByResourceId(pageable, resourceId);
    }

    @GetMapping("/byFaculty")
    public Response<GenericPageableResponse> findAllByFacultyId(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @RequestParam String facultyId){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.environmentService.findAllByFacultyId(pageable, facultyId);
    }

    @GetMapping("/byEnvironmentType")
    public Response<GenericPageableResponse> findAllByEnvironmentType(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order, @RequestParam EnvironmentTypeEnumeration environmentType){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return this.environmentService.findAllByEnvironmentType(pageable, environmentType);
    }


    @GetMapping("/{id}")
    public Response<EnvironmentDTO> findById(@PathVariable Integer id){
        return this.environmentService.getEnvironmentByCode(id);
    }

    @GetMapping("/allTypes")
    public List<EnvironmentTypeEnumeration> findAllTypes(){
        return this.environmentService.findAllTypesEnvironment();
    }
}
