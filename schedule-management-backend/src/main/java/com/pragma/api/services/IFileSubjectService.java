package com.pragma.api.services;

import com.pragma.api.util.file.templateclasses.FileRowSubject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileSubjectService {

    public List<String> uploadFile(MultipartFile file) throws IOException;

    public List<String> processFile(List<FileRowSubject> logs);

}
