package com.pragma.api.services;

import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileTeachersService {

    List<String> uploadFile(MultipartFile file) throws IOException;

    public List<String> processFile(List<FileRowTeacher> logs);

}
