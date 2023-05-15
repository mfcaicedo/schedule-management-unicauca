package com.pragma.api.util.file;

import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileTeachers extends ProcessFile<FileRowTeacher>{
    @Override
    public List<FileRowTeacher> getLogs(MultipartFile file) throws IOException {

        List<FileRowTeacher> fileRows = new ArrayList<>();
        InputStream fileExcel = file.getInputStream();
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);  //cargamos el archivo
        XSSFSheet sheet = book.getSheetAt(0);  //cargamos la hoja que vamos a tratar
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
            FileRowTeacher fileRow = convertCellsToFileRow(cells);
            fileRows.add(fileRow);
        }
        return fileRows;
    }

    @Override
    public FileRowTeacher convertCellsToFileRow(List<Cell> cells) {

        FileRowTeacher fileRow = new FileRowTeacher();
        fileRow.setRowNum(cells.get(0).getRowIndex());
        fileRow.setCode_teacher((int) cells.get(0).getNumericCellValue());
        fileRow.setName_teacher(cells.get(1).getStringCellValue());
        //fileRow.setCode_department((int) cells.get(2).getNumericCellValue());
        fileRow.setName_department(cells.get(2).getStringCellValue());
        return fileRow;
    }
}
