package com.pragma.api.util.file;

import com.pragma.api.domain.ResponseFile;
import com.pragma.api.model.enums.StateAcOfferFileEnumeration;
import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
    public List<FileRowAcademicOffer> getLogs(MultipartFile file, ResponseFile responseFile) throws IOException {

        List<FileRowAcademicOffer> fileRows = new ArrayList<>();

        InputStream fileExcel = file.getInputStream();
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);
        XSSFSheet sheet = book.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();
        System.out.println("FILAS EXCEL: " + rowNum);

        FileRowAcademicOffer fileRowAux = new FileRowAcademicOffer();
        //Recupero la información del archivo correspondiente a la clase academicOfferFile solo una vez
        fileRowAux.setDateRegisterFile(new Date());
        fileRowAux.setNameFile(file.getOriginalFilename());
        fileRowAux.setStateFile(StateAcOfferFileEnumeration.SIN_INICIAR);
        fileRowAux.setPeriod(sheet.getRow(4).getCell(1).getStringCellValue());
        fileRowAux.setProgramId(sheet.getRow(2).getCell(1).getStringCellValue());
        //agrego en la primera posición del array la información del archivo
        fileRows.add(fileRowAux);

        //Establezco el número de filas a recorrer.
        int rowNumOriginal = getRowNumOriginal(rowNum, sheet);
        System.out.println("filas a recorrer: " + rowNumOriginal);
        for (int i = 10; i <= rowNumOriginal; i++) {
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
        List<String> listAuxCodeTeachers = new ArrayList<>();
        if(cells.get(7).getCellType() != CellType.BLANK){
            listAuxCodeTeachers.add(cells.get(7).getStringCellValue().split("-")[0].trim());
        }
        if(cells.get(8).getCellType() != CellType.BLANK){
            listAuxCodeTeachers.add(cells.get(8).getStringCellValue().split("-")[0].trim());
        }
        if(cells.get(9).getCellType() != CellType.BLANK){
            listAuxCodeTeachers.add(cells.get(9).getStringCellValue().split("-")[0].trim());
        }
        fileRow.setPersonCode(listAuxCodeTeachers);
        return fileRow;
    }

    /**
     * Método para obtener el número de filas a recorrer
     * @param rowNum Número de filas del archivo
     * @param sheet Hoja del archivo
     * @return Número de filas a recorrer
     */
    private int getRowNumOriginal(int rowNum, Sheet sheet) {
        int count = 0;
        //fijamos un límite de filas vacías consecutivas que al encontrarse significa que el archivo ha terminado
        int limit = 5;
        //Recorro las filas del archivo
        for (int i = 10; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            //Obtengo las celdas 1 y 2 de la fila actual
            Cell cell0 = row.getCell(0);
            Cell cell1 = row.getCell(1);
            //Verifico si las celdas son nulas o vacías para la celda 0
            if (cell0 == null || cell0.getCellType() == CellType.BLANK){
                //Verifico si las celdas son nulas o vacías para celda 1
                if (cell1 == null || cell1.getCellType() == CellType.BLANK){
                    //Si la resta del total de filas menos la fila actual es menor o igual al límite
                    // y el contador es 0, entonces el límite será la resta de total de filas menos la fila actual más 1
                    if (rowNum-i <= limit && count == 0) limit = rowNum-i+1;
                    //Incremento el contador de filas vacías consecutivas
                    count++;
                }
                //Si el contador es igual al límite, entonces retorno la fila actual menos el límite
                // que sería la última fila con información, es decir, la última fila a recorrer
                if (count == limit) return i-limit;
            }else{
                //Si la celda 0 no es nula ni vacía, entonces reinicio el contador
                count = 0;
            }
        }
        return rowNum;
    }
    public void getPrueba(ResponseFile responseFile){
        responseFile.addLogsGeneric("Esto es una prueba");
    }

}
