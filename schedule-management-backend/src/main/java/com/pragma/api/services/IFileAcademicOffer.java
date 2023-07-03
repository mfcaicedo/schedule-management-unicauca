package com.pragma.api.services;

import com.pragma.api.domain.AcademicOfferFileDTO;
import com.pragma.api.domain.GenericPageableResponse;
import com.pragma.api.domain.Response;
import com.pragma.api.util.file.FileAcademicOffer;
import org.springframework.data.domain.Pageable;
import com.pragma.api.domain.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileAcademicOffer {
    /**
     * Metodo que me permite procesar el archivo de oferta academica y subirlo a la
     * base de datos
     *
     * @param file archivo a procesar excel
     * @return lista de logs con el resultado del proceso
     * @throws IOException excepcion de entrada y salida
     */
    ResponseFile uploadFile(MultipartFile file) throws IOException;

    Response<GenericPageableResponse> findAll(final Pageable pageable);

    /**
     * Metodo que me permite actualizar el estado de un archivo de oferta academica
     * @param id id del archivo de oferta academica
     * @param stateFile estado del archivo de oferta academica
     * @return archivo de oferta academica actualizado
     */
    Response<AcademicOfferFileDTO> updateStateFile(final Integer id, final String stateFile);

    Response<List<AcademicOfferFileDTO>> findAllByStatefile(final String stateFile);

}
