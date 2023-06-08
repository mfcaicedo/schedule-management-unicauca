package com.pragma.api.util.file;

import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

        FileRowAcademicOffer fileRowAux = new FileRowAcademicOffer();
        //Recupero la información del archivo correspondiente a la clase academicOfferFile solo una vez
        fileRowAux.setDateRegisterFile(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println("Fecha de registro verrrr: " + fileRowAux.getDateRegisterFile());
        fileRowAux.setNameFile(file.getName());
        fileRowAux.setStateFile(StateAcOfferFileEnumeration.SIN_INICIAR);
        fileRowAux.setPeriod(sheet.getRow(4).getCell(1).getStringCellValue());
        fileRowAux.setProgramId(sheet.getRow(2).getCell(1).getStringCellValue());
        //agrego en la primera posición del array la información del archivo
        fileRows.add(fileRowAux);

        for (int i = 10; i <= rowNum; i++) {
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
        //Course
        fileRow.setCapacity((int)cells.get(5).getNumericCellValue());
        fileRow.setGroup(cells.get(4).getStringCellValue());
        fileRow.setTypeEnvironmentRequired(cells.get(6).getStringCellValue());
        fileRow.setWeeklyOverload((int)cells.get(3).getNumericCellValue());
        fileRow.setInBlock(cells.get(2).getStringCellValue().equals("SI") ? true : false);
        fileRow.setSubjectCode(cells.get(1).getStringCellValue().split("-")[0].trim());

        //CourseTecher
        fileRow.setPersonCode(
                Arrays.asList(cells.get(7).getStringCellValue().split("-")[0].trim(),
                cells.get(8).getStringCellValue().split("-")[0].trim(),
                cells.get(9).getStringCellValue().split("-")[0].trim()
                )); //Posible error

        return fileRow;
    }

}
