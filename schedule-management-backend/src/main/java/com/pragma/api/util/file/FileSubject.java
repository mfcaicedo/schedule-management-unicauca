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
        System.out.println("CELLS SIZE: " + cells.size());
//        System.out.println("COLUMNA 1: ." + cells.get(1) + ".");
        FileRowSubject fileRow = new FileRowSubject(-1, "", "", -1, "", -1, "");
        if (cells.get(0) != null) {
            fileRow.setRowNum(cells.get(0).getRowIndex());
            fileRow.setSubjectCode(cells.get(0).getStringCellValue());
        }
        if (cells.get(1) != null) {
            fileRow.setRowNum(cells.get(1).getRowIndex());
            fileRow.setName(cells.get(1).getStringCellValue());
        }
        if (cells.get(2) != null) {
            fileRow.setRowNum(cells.get(2).getRowIndex());
            fileRow.setSemester((int) cells.get(2).getNumericCellValue());
        }
        if (cells.get(3) != null) {
            fileRow.setRowNum(cells.get(3).getRowIndex());
            fileRow.setInBlock(cells.get(3).getStringCellValue());
        }
        if (cells.get(4) != null) {
            fileRow.setRowNum(cells.get(4).getRowIndex());
            fileRow.setWeeklyOverload((int) cells.get(4).getNumericCellValue());
        }
        if (cells.size() > 5 && cells.get(5) != null) {
            fileRow.setRowNum(cells.get(5).getRowIndex());
            fileRow.setProgramWhitCode(cells.get(5).getStringCellValue());
        }
        return fileRow;
    }
}
