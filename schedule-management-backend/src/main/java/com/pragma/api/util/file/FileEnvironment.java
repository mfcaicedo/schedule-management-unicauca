package com.pragma.api.util.file;

import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileEnvironment extends ProcessFile<FileRowEnvironment>{
    @Override
    public List<FileRowEnvironment> getLogs(MultipartFile file) throws IOException {
        List<FileRowEnvironment> fileRows = new ArrayList<>();


        InputStream fileExcel = file.getInputStream();


        XSSFWorkbook book = new XSSFWorkbook(fileExcel);


        XSSFSheet sheet = book.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        System.out.println(rowNum);

        //VERIFICAR EL INICIO DE ESTE FOR
        for (int i = 1; i <= rowNum;i++) {
            List<Cell> cells = new ArrayList<>();
            System.out.println("Registro numero: " + i);

            Row row = sheet.getRow(i);

            int columnNum = row.getLastCellNum();

            for (int j = 0; j < columnNum; j++) {
                cells.add(row.getCell(j));
            }

            FileRowEnvironment fileRow = convertCellsToFileRow(cells);
            fileRows.add(fileRow);

        }

        return fileRows;
    }

    @Override
    public FileRowEnvironment convertCellsToFileRow(List<Cell> cells) {
        FileRowEnvironment fileRow = new FileRowEnvironment();
        fileRow.setName(cells.get(0).getStringCellValue());
        fileRow.setLocation(cells.get(1).getStringCellValue());
        fileRow.setCapacity((int)cells.get(2).getNumericCellValue());
        fileRow.setAvailableResources(cells.get(3).getStringCellValue());
        fileRow.setEnvironmentType(cells.get(4).getStringCellValue());
        fileRow.setFaculty(cells.get(5).getStringCellValue());
        return fileRow;
    }
}
