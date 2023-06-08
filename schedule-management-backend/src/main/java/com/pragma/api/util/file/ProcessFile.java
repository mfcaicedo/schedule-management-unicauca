package com.pragma.api.util.file;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public abstract class ProcessFile<T> {
    /**
     * Método para recopilar la información de las celdas y convertiras en propiedades del objeto T.
     * @param file archivo excel
     * @return Retorna una lista de objetos T diferente para cada tipo de archivo
     * @throws IOException Excepción de entrada y salida
     */
    public abstract List<T> getLogs(MultipartFile file) throws IOException;
    /**
     * Método para convertir las celdas de un archivo excel a un objeto de tipo FileRow
     * (Columna completa)
     * @param cells celdas de una fila del archivo excel
     * @return Retorna un objeto T diferente para cada tipo de archivo
     */
    public abstract T convertCellsToFileRow(List<Cell> cells);
}
