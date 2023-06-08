package com.pragma.api.services;

import com.pragma.api.domain.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileAcademicOffer {
    /**
     * Metodo que me permite procesar el archivo de oferta academica y subirlo a la base de datos
     * @param file archivo a procesar excel
     * @return lista de logs con el resultado del proceso
     * @throws IOException excepcion de entrada y salida
     */
    ResponseFile uploadFile(MultipartFile file) throws IOException;

}
