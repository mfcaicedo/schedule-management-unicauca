package com.pragma.api.services;

import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileAcademicOffer {

    List<String> uploadFile(MultipartFile file) throws IOException;

    Response<GenericPageableResponse> findAll(final Pageable pageable);

}
