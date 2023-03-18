package com.pragma.api.business;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileBusiness {

    List<String> uploadFile(MultipartFile file) throws IOException;

}
