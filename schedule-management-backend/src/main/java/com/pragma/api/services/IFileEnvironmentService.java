package com.pragma.api.services;

import com.pragma.api.domain.ResponseFile;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileEnvironmentService {
    public ResponseFile uploadFile(MultipartFile file) throws IOException;
    public ResponseFile processFile(List<FileRowEnvironment> logs);

}
