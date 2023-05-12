package com.pragma.api.util.file;

import com.pragma.api.util.file.templateclasses.FileRowSubject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileSubject extends ProcessFile<FileRowSubject> {

    @Override
    public List<FileRowSubject> getLogs(MultipartFile file) throws IOException {
        List<FileRowSubject> fileRows = new ArrayList<>();
        InputStream fileExcel = file.getInputStream();
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);
        XSSFSheet sheet = book.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        System.out.println("FILAS EXCEL: " + rowNum);
        for (int i = 1; i <= rowNum; i++) {
            List<Cell> cells = new ArrayList<>();
            Row row = sheet.getRow(i);
            int columnNum = row.getLastCellNum();
            for (int j = 0; j < columnNum; j++) {
                cells.add(row.getCell(j));
            }
            FileRowSubject fileRow = convertCellsToFileRow(cells);
            fileRows.add(fileRow);
        }

        return fileRows;
    }

    @Override
    public FileRowSubject convertCellsToFileRow(List<Cell> cells) {
        FileRowSubject fileRow = new FileRowSubject();
        fileRow.setRowNum(cells.get(0).getRowIndex());
        fileRow.setSubjectCode(cells.get(0).getStringCellValue());
        fileRow.setName(cells.get(1).getStringCellValue());
        fileRow.setSemester((int) cells.get(2).getNumericCellValue());
        fileRow.setInBlock(cells.get(3).getStringCellValue());
        fileRow.setWeeklyOverload((int) cells.get(4).getNumericCellValue());
        fileRow.setProgramWhitCode(cells.get(5).getStringCellValue());

        return fileRow;
    }
}
