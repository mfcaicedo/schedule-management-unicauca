package com.pragma.api.services;

import com.pragma.api.util.file.templateclasses.FileRowSubject;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.api.domain.ResponseFile;
import java.io.IOException;
import java.util.List;

public interface IFileSubjectService {

//    public List<String> uploadFile(MultipartFile file) throws IOException;
    public ResponseFile uploadFile(MultipartFile file) throws IOException;

    public ResponseFile processFile(List<FileRowSubject> logs);

}
