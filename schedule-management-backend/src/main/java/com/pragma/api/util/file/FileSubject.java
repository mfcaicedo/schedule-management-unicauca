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
import org.apache.poi.ss.usermodel.CellType;

public class FileSubject extends ProcessFile<FileRowSubject> {

    private final int filaInicial = 6;
    private final int filaPrograma = 2;
    private final int columnPrograma = 1;
    private String programCode = "";

    @Override
    public List<FileRowSubject> getLogs(MultipartFile file) throws IOException {
        List<FileRowSubject> fileRows = new ArrayList<>();
        InputStream fileExcel = file.getInputStream();
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);
        XSSFSheet sheet = book.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
//        System.out.println("FILAS EXCEL: " + rowNum);
        Row rowProgram = sheet.getRow(this.filaPrograma);
        if (rowProgram.getLastCellNum() >= 1) {
            this.programCode = rowProgram.getCell(this.columnPrograma).getStringCellValue();
        }
        for (int i = this.filaInicial; i <= rowNum; i++) {
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
//        System.out.println("CELLS SIZE: " + cells.size());
//        System.out.println("COLUMNA 1: ." + cells.get(0) + ".");
        FileRowSubject fileRow = new FileRowSubject(-1, "", "", -1, "", -1, "");
        fileRow.setProgramCode(this.programCode);

        if (cells.size() > 0 && cells.get(0) != null) {
            if (cells.get(0).getCellType().equals(CellType.STRING)) {
                String subjectCode = cells.get(0).getStringCellValue().trim();
                if (subjectCode.length() > 0) {
                    fileRow.setRowNum(cells.get(0).getRowIndex());
                    fileRow.setSubjectCode(subjectCode.toUpperCase());
                }
            }
        }
        if (cells.size() > 1 && cells.get(1) != null) {
            if (cells.get(1).getCellType().equals(CellType.STRING)) {
                String name = cells.get(1).getStringCellValue().trim();
                if (name.length() > 0) {
                    fileRow.setRowNum(cells.get(1).getRowIndex());
                    fileRow.setName(name.toUpperCase());
                }
            }
        }
        if (cells.size() > 2 && cells.get(2) != null) {
            if (cells.get(2).getCellType().equals(CellType.NUMERIC)) {
                fileRow.setRowNum(cells.get(2).getRowIndex());
                fileRow.setSemester((int) cells.get(2).getNumericCellValue());
            } else {
                fileRow.setSemester(-2);
            }
        }
        if (cells.size() > 3 && cells.get(3) != null) {
            if (cells.get(3).getCellType().equals(CellType.STRING)) {
                String inBlock = cells.get(3).getStringCellValue().trim();
                if (inBlock.length() > 0) {
                    fileRow.setRowNum(cells.get(3).getRowIndex());
                    fileRow.setInBlock(inBlock.toUpperCase());
                }
            }
        }
        if (cells.size() > 4 && cells.get(4) != null) {
            if (cells.get(4).getCellType().equals(CellType.NUMERIC)) {
                fileRow.setRowNum(cells.get(4).getRowIndex());
                fileRow.setWeeklyOverload((int) cells.get(4).getNumericCellValue());
            } else {
                fileRow.setWeeklyOverload(-2);
            }
        }
        return fileRow;
    }
}
