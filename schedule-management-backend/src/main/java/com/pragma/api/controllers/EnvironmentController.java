package com.pragma.api.controllers;

import com.pragma.api.domain.*;
import com.pragma.api.services.IEnvironmentService;
import com.pragma.api.model.enums.EnvironmentTypeEnumeration;
import com.pragma.api.model.enums.RecurrenceEnumeration;
import com.pragma.api.services.IFileEnvironmentService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/environment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnvironmentController {

    private final IEnvironmentService environmentService;
    private final IFileEnvironmentService fileEnvironmentService;

    public EnvironmentController(IEnvironmentService environmentService,
                                 IFileEnvironmentService fileEnvironmentService) {
        this.environmentService = environmentService;
        this.fileEnvironmentService = fileEnvironmentService;
    }

    @PostMapping()
    public Response<EnvironmentDTO> createEnvironment(@Valid @RequestBody EnvironmentDTO environmentDTO) {
        System.out.println(environmentDTO);
        return this.environmentService.createEnvironment(environmentDTO);
    }

    @PostMapping("/uploadFile")
    ResponseEntity<ResponseFile> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.fileEnvironmentService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadTemplate")
    ResponseEntity<Resource> downloadTemplate() throws IOException {
        System.out.println("--------------------------------lleaga al controlador de descarga");
        return this.fileEnvironmentService.downloadTemplateFile();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public Response<EnvironmentDTO> updateEnvironment(@Valid @RequestBody EnvironmentDTO environmentDTO,
                                                      @PathVariable Integer id) {
        System.out.println("llega al controlador de update");
        return this.environmentService.updateEnvironment(environmentDTO, id);
    }

    @PostMapping("/addResourceForm")
    public Response<Boolean> addResourceToEnvironmentForm(@RequestParam List<Integer> resourceList,
                                                                  @RequestParam Integer environmentId) {
        return this.environmentService.addResourceToEnvironmentForm(resourceList, environmentId);
    }
    @PostMapping("/addResource")
    public Response<Boolean> addResourceToEnvironment(@RequestBody ResourceList resourceList,
                                                      @RequestParam Integer environmentId) {
        return this.environmentService.addResourceToEnvironment(resourceList, environmentId);
    }

    @PutMapping("/updateResource")
    public Response<Boolean> updateResourceToEnvironment(@RequestBody ResourceList resourceList,
                                                         @RequestParam Integer environmentId) {
        return this.environmentService.updateResourceToEnvironment(resourceList, environmentId);
    }

    @GetMapping()
    public Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size,
                                                     @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.environmentService.findAll(pageable);
    }

    @GetMapping("/byResource")
    public Response<GenericPageableResponse> findAllByResourceId(@RequestParam Integer page, @RequestParam Integer size,
                                                                 @RequestParam String sort, @RequestParam String order, @RequestParam Integer resourceId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.environmentService.findAllByResourceId(pageable, resourceId);
    }

    @GetMapping("/byFaculty")
    public Response<GenericPageableResponse> findAllByFacultyId(@RequestParam Integer page, @RequestParam Integer size,
                                                                @RequestParam String sort, @RequestParam String order, @RequestParam String facultyId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.environmentService.findAllByFacultyId(pageable, facultyId);
    }

    @GetMapping("/byEnvironmentType")
    public Response<GenericPageableResponse> findAllByEnvironmentType(@RequestParam Integer page,
                                                                      @RequestParam Integer size, @RequestParam String sort, @RequestParam String order,
                                                                      @RequestParam EnvironmentTypeEnumeration environmentType) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.environmentService.findAllByEnvironmentType(pageable, environmentType);
    }

    //Consulta para buscar los salones/auditorios/laboratorios de un edificio en concreto
    @GetMapping("/byTypeAndParentId/{environmentType}/{parentId}")
    public Response<List<EnvironmentDTO>> findAllByTypeAndParentId(@PathVariable EnvironmentTypeEnumeration environmentType, @PathVariable Integer parentId) {
        //Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        //return this.environmentService.findAllByTypeAndParentId(EnvironmentTypeEnumeration.LABORATORIO, parentId);
        return this.environmentService.findAllByTypeAndParentId(environmentType, parentId);
    }

    @GetMapping("/{id}")
    public Response<EnvironmentDTO> findById(@PathVariable Integer id) {
        return this.environmentService.getEnvironmentByCode(id);
    }

    @PreAuthorize("hasRole('ROLE_ACADEMIC_MANAGER')")
    @GetMapping("/allTypes")
    public List<EnvironmentTypeEnumeration> findAllTypes() {
        return this.environmentService.findAllTypesEnvironment();
    }

    @DeleteMapping("/delete/{environmentId}")
    public void deleteEnvironment(@PathVariable Integer environmentId) {
        System.out.println("id" + environmentId);
        this.environmentService.deleteById(environmentId);
    }

    @PreAuthorize("hasRole('ROLE_SCHEDULE_MANAGER')")
    @PostMapping("/findEnviromentAvailability")
    public Response<GenericPageableResponse> findEnviromentAvailability(@RequestParam Integer page, @RequestParam Integer size,                                            
            @Valid @RequestBody AvailabilityEnvironmentDTO environmentAvailabilityDTO) {

        Pageable pageable = PageRequest.of(page, size); 
        
        if (environmentAvailabilityDTO.getRecurrence().equals(RecurrenceEnumeration.DIA)) {
            return this.environmentService.findAllByAvailabilityAndDayRecurrence(
                    environmentAvailabilityDTO.getStarting_date(),
                    environmentAvailabilityDTO.getStarting_time(), environmentAvailabilityDTO.getEnding_time(),pageable);

        } else if (environmentAvailabilityDTO.getRecurrence().equals(RecurrenceEnumeration.SEMANA)) {
            return this.environmentService.findAllByAvailabilityAndWeekRecurrence(
                    environmentAvailabilityDTO.getStarting_date(),
                    environmentAvailabilityDTO.getStarting_time(), environmentAvailabilityDTO.getEnding_time(),
                    environmentAvailabilityDTO.getDay(), environmentAvailabilityDTO.getWeeks(),pageable);

        } else if (environmentAvailabilityDTO.getRecurrence().equals(RecurrenceEnumeration.SEMESTRE)) {
            return this.environmentService.findAllByAvailabilityAndSemesterRecurrence(
                    environmentAvailabilityDTO.getStarting_time(), environmentAvailabilityDTO.getEnding_time(),
                    environmentAvailabilityDTO.getDay(),pageable);

        }
        return null;

    }

    //metodo para consultar todos los edificios a partir de un id facultad
    @GetMapping("/consultBuildingsByFacultyId/{id}")
    public Response<List<EnvironmentDTO>> consultBuildingsByFacultyId(@PathVariable String id) {
        return this.environmentService.findAllBuildings(id);
    }

    //Metodo para obtener todos los ambientes para ser listados, junto con su id, name y type
    @GetMapping("/consultEnvironmentsByFacultyId/{id}")
    public Response<List<EnvironmentDTO>> consultEnvironmentsByFacultyId(@PathVariable String id) {
        return this.environmentService.findAllEnvironmentByIdFacultyAndBuilding(id);
    }
}