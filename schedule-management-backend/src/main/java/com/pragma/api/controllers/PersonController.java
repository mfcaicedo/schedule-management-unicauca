package com.pragma.api.controllers;

import com.pragma.api.services.IPersonService;
import com.pragma.api.domain.GenericPageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {

    private final IPersonService iPersonService;

    @Autowired

    public PersonController(IPersonService iPersonService) {
        this.iPersonService = iPersonService;
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse> getAllPerson(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sort, @RequestParam String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order),sort));
        return ResponseEntity.status(HttpStatus.OK).body(this.iPersonService.findAllPerson(pageable));
    }
}
