package com.pragma.api.services;

import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileEnvironmentService {
    public List<String> uploadFile(MultipartFile file) throws IOException;
    public List<String> processFile(List<FileRowEnvironment> logs);

}
