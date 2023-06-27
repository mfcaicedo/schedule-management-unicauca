package com.pragma.api.services;

import com.pragma.api.domain.Response;
import com.pragma.api.domain.TemplateSubjectDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ITemplateSubjectService {

    public ResponseEntity<Resource> downloadTemplateSubject(String programId) throws IOException;

}
