package com.pragma.api.controllers;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {
    
    private final IReportService iReportService;

}
