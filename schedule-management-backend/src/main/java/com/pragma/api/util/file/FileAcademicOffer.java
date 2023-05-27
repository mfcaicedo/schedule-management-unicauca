package com.pragma.api.util.file;

import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileAcademicOffer extends ProcessFile<FileRowAcademicOffer> {

    @Override
    public List<FileRowAcademicOffer> getLogs(MultipartFile file) throws IOException {

        List<FileRowAcademicOffer> fileRows = new ArrayList<>();

        InputStream fileExcel = file.getInputStream();

        XSSFWorkbook book = new XSSFWorkbook(fileExcel);

        XSSFSheet sheet = book.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        System.out.println("FILAS EXCEL: " + rowNum);

        for (int i = 1; i <= rowNum; i++) {
            List<Cell> cells = new ArrayList<>();
            System.out.println("Registro numero: " + i);

            Row row = sheet.getRow(i);

            int columnNum = row.getLastCellNum();

            for (int j = 0; j < columnNum; j++) {
                cells.add(row.getCell(j));
            }

            FileRowAcademicOffer fileRow = convertCellsToFileRow(cells);
            fileRows.add(fileRow);

        }
        return fileRows;
    }

    @Override
    public FileRowAcademicOffer convertCellsToFileRow(List<Cell> cells) {

        FileRowAcademicOffer fileRow = new FileRowAcademicOffer();

        fileRow.setPeriod(cells.get(0).getStringCellValue());
        fileRow.setProgram(cells.get(1).getStringCellValue());
        fileRow.setSemester((int) cells.get(2).getNumericCellValue());
        fileRow.setSubjectCode(cells.get(3).getStringCellValue());
        fileRow.setSubjectName(cells.get(4).getStringCellValue());
        fileRow.setDailyOverload((int) cells.get(5).getNumericCellValue());
        fileRow.setWeeklyOverload((int) cells.get(6).getNumericCellValue());
        fileRow.setGroup(cells.get(7).getStringCellValue());
        fileRow.setCapacity((int) cells.get(8).getNumericCellValue());
        fileRow.setEnvironment(cells.get(9).getStringCellValue());
        // EL TIPO DE DATO ERA NUMERICO (Codigo Docente), AHORA ES UN STRING (Varios codigos separados por comas)
//        fileRow.setTeacherCode(String.valueOf((int)cells.get(10).getNumericCellValue()));
        fileRow.setPersonCode(cells.get(10).getStringCellValue());
        fileRow.setDescription(cells.get(11).getStringCellValue());
        fileRow.setDepartment(cells.get(12).getStringCellValue());

        return fileRow;
    }

}
