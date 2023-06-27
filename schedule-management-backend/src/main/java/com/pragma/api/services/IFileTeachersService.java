package com.pragma.api.services;

import com.pragma.api.domain.ResponseFile;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileTeachersService {

    public ResponseFile uploadFile(MultipartFile file) throws IOException;

    public ResponseFile processFile(List<FileRowTeacher> logs, ResponseFile responseFile);

    public ResponseEntity<Resource> donwloadTeacherTemplateFile() throws IOException;

}
