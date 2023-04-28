package com.pragma.api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileAcademicOffer {

    List<String> uploadFile(MultipartFile file) throws IOException;

}
