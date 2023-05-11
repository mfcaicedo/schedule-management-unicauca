package com.pragma.api.services;

import com.pragma.api.domain.TemplateFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ITemplateFileService {
    public TemplateFileDTO uploadTemplateFile(MultipartFile file) throws IOException;
}
