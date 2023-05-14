package com.pragma.api.services;

import com.pragma.api.domain.Response;
import com.pragma.api.domain.TemplateFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ITemplateFileService  {
    public TemplateFileDTO uploadTemplateFile(MultipartFile file) throws IOException;

    public ResponseEntity<Resource> donwloadTemplateFile(String programId) throws IOException;

}
