package com.pragma.api.util.file;

import com.pragma.api.domain.ResponseFile;
import com.pragma.api.model.enums.StatusFileEnumeration;
import com.pragma.api.util.file.templateclasses.FileRowAcademicOffer;
import com.pragma.api.util.file.templateclasses.FileRowTeacher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileTeachers extends ProcessFile<FileRowTeacher>{
    @Override
    public List<FileRowTeacher> getLogs(MultipartFile file, ResponseFile responseFile) throws IOException {

        List<FileRowTeacher> fileRows = new ArrayList<>();
        InputStream fileExcel = file.getInputStream();
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);  //cargamos el archivo
        XSSFSheet sheet = book.getSheetAt(0);  //cargamos la hoja que vamos a tratar
        int rowNum = sheet.getLastRowNum();
        int rowNumOriginal = getRowNumOriginal(rowNum, sheet);
        System.out.println("FILAS EXCEL suceso : " + rowNumOriginal);
        if (rowNumOriginal == 0){
            responseFile.addLogsGeneric("El archivo no contiene registros");
            responseFile.setStatusFile(StatusFileEnumeration.ERROR);
            return fileRows;
        }
        System.out.println("FILAS EXCEL: " + rowNum);
        for (int i = 1; i <= rowNumOriginal; i++) {
            List<Cell> cells = new ArrayList<>();
            System.out.println("Registro numero: " + i);
            Row row = sheet.getRow(i);
            int columnNum = row.getLastCellNum();
            for (int j = 0; j < columnNum; j++) {
                cells.add(row.getCell(j));
            }
            FileRowTeacher fileRow = convertCellsToFileRow(cells, responseFile);
            fileRows.add(fileRow);
        }
        return fileRows;
    }

    @Override
    public FileRowTeacher convertCellsToFileRow(List<Cell> cells, ResponseFile responseFile) {

        System.out.println("CELLS SIZE: " + cells.size());

        //FileRowTeacher fileRow = new FileRowTeacher();
        FileRowTeacher fileRow = new FileRowTeacher(-1,0,"","");

        if (cells.get(0) != null) {
            fileRow.setRowNum(cells.get(0).getRowIndex());
            fileRow.setCode_teacher((int) cells.get(0).getNumericCellValue());
        }

        if (cells.get(1) != null) {
            if(cells.get(1).getCellType() == CellType.STRING){
               fileRow.setName_teacher(cells.get(1).getStringCellValue());
            }
        }

        if (cells.get(2) != null) {
            if(cells.get(2).getCellType() == CellType.STRING){
                fileRow.setName_department(cells.get(2).getStringCellValue());
            }
        }

        return fileRow;
    }

    private int getRowNumOriginal(int rowNum, Sheet sheet) {
        int count = 0;
        //fijamos un límite de filas vacías consecutivas que al encontrarse significa que el archivo ha terminado
        int limit = 5;
        //Recorro las filas del archivo
        for (int i = 1; i <= rowNum; i++) {
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
}
