package com.pragma.api.controllers;

import com.pragma.api.domain.*;
import com.pragma.api.services.IFileAcademicOffer;
import com.pragma.api.services.ITemplateFileService;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/academicOffer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AcademicOfferController {

    //test a
    private final IFileAcademicOffer iFileAcademicOffer;

    private final ITemplateFileService iTemplateFileService;

    @Autowired
    public AcademicOfferController(IFileAcademicOffer iFileAcademicOffer, ITemplateFileService iTemplateFileService) {
        this.iFileAcademicOffer = iFileAcademicOffer;
        this.iTemplateFileService = iTemplateFileService;
    }

    @PostMapping("/uploadFile")
    ResponseEntity<ResponseFile> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.iFileAcademicOffer.uploadFile(file), HttpStatus.OK);
    }

    @PostMapping("/uploadTemplate")
    ResponseEntity<TemplateFileDTO> uploadTemplate(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.iTemplateFileService.uploadTemplateFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadTemplate/{programId}")
    ResponseEntity<ResponseExcel> downloadTemplate(@PathVariable String programId) throws IOException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + "template" + programId + ".xlsx" + "\"")
                .body(this.iTemplateFileService.donwloadTemplateFile(programId));
    }

    @GetMapping()
    public Response<GenericPageableResponse> findAll(@RequestParam Integer page, @RequestParam Integer size,
            @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return this.iFileAcademicOffer.findAll(pageable);
    }

    @PutMapping("/updateStateFile")
    public Response<AcademicOfferFileDTO> updateStateFile(@RequestParam Integer id, @RequestParam String stateFile) {
        return this.iFileAcademicOffer.updateStateFile(id, stateFile);
    }

    @GetMapping("/findAllByStatefile")
    public Response<List<AcademicOfferFileDTO>> findAllByStatefile(@RequestParam String stateFile) {
        return this.iFileAcademicOffer.findAllByStatefile(stateFile);
    }
    /**
     *  @PutMapping("/updateStateFile")
     *     public Response<AcademicOfferFileDTO> updateStateFile(@RequestParam Integer id, @RequestParam String stateFile) {
     *         return this.iFileAcademicOffer.updateStateFile(id, stateFile);
     *     }
     *
     *     @GetMapping("/findAllByStatefile")
     *     public Response<List<AcademicOfferFileDTO>> findAllByStatefile(@RequestParam String stateFile) {
     *         return this.iFileAcademicOffer.findAllByStatefile(stateFile);
     *     }
     */

}
