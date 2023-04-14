package com.pragma.api.util.file;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public abstract class ProcessFile<T> {

    public abstract List<T> getLogs(MultipartFile file) throws IOException;
    public abstract T convertCellsToFileRow(List<Cell> cells);
}
