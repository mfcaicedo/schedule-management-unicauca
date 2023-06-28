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
import java.util.ArrayList;
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
        if(sheet.getRow(4).getCell(1) == null ||
                sheet.getRow(4).getCell(1).getCellType().equals(CellType.BLANK)){
            responseFile.addLogsEmptyFields("[Fila: 5 - Columna: B]  El periodo está vacío");
        }else if(sheet.getRow(4).getCell(1).getCellType().equals(CellType.STRING)){
            fileRowAux.setPeriod(sheet.getRow(4).getCell(1).getStringCellValue());
        }else{
            responseFile.addLogsType("[Fila: 5 Columna: B] El periodo debe ser tipo texto");
        }
        if(sheet.getRow(2).getCell(1) == null ||
                sheet.getRow(2).getCell(1).getCellType().equals(CellType.BLANK)){
            responseFile.addLogsEmptyFields("[Fila: 3 - Columna: B]  El código del programa está vacío");
        } else if(sheet.getRow(2).getCell(1).getCellType().equals(CellType.STRING)){
            fileRowAux.setProgramId(sheet.getRow(2).getCell(1).getStringCellValue());
        }else{
            responseFile.addLogsType("[Fila: 3 Columna: B] El programa debe ser tipo texto");
        }
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
            FileRowAcademicOffer fileRow = convertCellsToFileRow(cells, responseFile);
            fileRows.add(fileRow);
        }
        return fileRows;
    }

    @Override
    public FileRowAcademicOffer convertCellsToFileRow(List<Cell> cells, ResponseFile responseFile) {

        FileRowAcademicOffer fileRow = new FileRowAcademicOffer();
        //Course
        //Capacidad
        if (cells.get(5) == null || cells.get(5).getCellType().equals(CellType.BLANK)) {
            responseFile.addLogsEmptyFields("[Fila: " + (cells.get(5).getRowIndex() + 1) + " Columna: F] La capacidad del " +
                    "curso está vacía");
        } else if (cells.get(5).getCellType().equals(CellType.NUMERIC)) {
            fileRow.setCapacity((int) cells.get(5).getNumericCellValue());
        } else {
            responseFile.addLogsType("[Fila: " + (cells.get(5).getRowIndex() + 1) + " Columna: F] La capacidad " +
                    "debe ser tipo numérico");
        }
        //Grupo
        if (cells.get(4) == null || cells.get(4).getCellType().equals(CellType.BLANK)) {
            responseFile.addLogsEmptyFields("[Fila: " + (cells.get(4).getRowIndex() + 1) + " Columna: E] El grupo " +
                    "está vacío");
        } else if (cells.get(4).getCellType().equals(CellType.STRING)) {
            fileRow.setGroup(cells.get(4).getStringCellValue());
        } else {
            responseFile.addLogsType("[Fila: " + (cells.get(4).getRowIndex() + 1) + " Columna: E] El grupo " +
                    "debe ser tipo texto");
        }
        //Tipo de ambiente requerido
        if (cells.get(6) == null || cells.get(6).getCellType().equals(CellType.BLANK)) {
            responseFile.addLogsEmptyFields("[Fila: " + (cells.get(6).getRowIndex() + 1) + " Columna: G] El tipo de " +
                    "ambiente requerido está vacío");
        } else if (cells.get(6).getCellType().equals(CellType.STRING)) {
            fileRow.setTypeEnvironmentRequired(cells.get(6).getStringCellValue());
        } else {
            responseFile.addLogsType("[Fila: " + (cells.get(6).getRowIndex() + 1) + " Columna: G] El tipo de " +
                    "ambiente debe ser tipo texto");
        }
        //intensidad semanal
        if(cells.get(3) == null || cells.get(3).getCellType().equals(CellType.FORMULA)){
            fileRow.setWeeklyOverload((int)cells.get(3).getNumericCellValue());
        }else{
            responseFile.addLogsType("[Fila: " + (cells.get(3).getRowIndex() + 1) + " Columna: D] La celda de intensidad" +
                    " semanal no debe modificarse");
        }
        if(cells.get(2).getCellType().equals(CellType.FORMULA)){
            fileRow.setInBlock(cells.get(2).getStringCellValue().equals("SI") ? true : false);
        }else{
            responseFile.addLogsType("[Fila: " + (cells.get(2).getRowIndex() + 1) + " Columna: C] La celda de bloque" +
                    " no debe modificarse");
        }
        System.out.println("Celda verr: " + cells.get(1).getCellType());
        if(cells.get(1).getCellType().equals(CellType.STRING)){
            System.out.println("entra al condicional");
            fileRow.setSubjectCode(cells.get(1).getStringCellValue().split("-")[0].trim());
        }else{
            responseFile.addLogsType("[Fila: " + (cells.get(1).getRowIndex() + 1) + " Columna: B] Solo debes " +
                    "seleccionar la materia, evita escribir en la celda");
        }

        //CourseTecher
        List<String> listAuxCodeTeachers = new ArrayList<>();
        if(cells.get(7).getCellType() != CellType.BLANK){
            System.out.println("Celda 7: " + cells.get(7).getCellType());
            if (cells.get(7).getCellType().equals(CellType.STRING)){
                listAuxCodeTeachers.add(cells.get(7).getStringCellValue().split("-")[0].trim());
            }else{
                responseFile.addLogsType("[Fila: " + (cells.get(7).getRowIndex() + 1) + " Columna: H] Solo debes" +
                        " seleccionar el docente, evita escribir en la celda");
            }
        }else{
            responseFile.addLogsEmptyFields("[Fila: " + (cells.get(7).getRowIndex() + 1) + " Columna: H] Se " +
                    "debe seleccionar al menos un docente");
        }
        if(cells.get(8).getCellType() != CellType.BLANK){
            if (cells.get(8).getCellType().equals(CellType.STRING)){
            listAuxCodeTeachers.add(cells.get(8).getStringCellValue().split("-")[0].trim());
            }else{
                responseFile.addLogsType("[Fila: " + (cells.get(8).getRowIndex() + 1) + " Columna: I] Solo debes" +
                        " seleccionar el docente, evita escribir en la celda");
            }
        }
        if(cells.get(9).getCellType() != CellType.BLANK){
            System.out.println("Celda 9: " + cells.get(9).getCellType());
            if (cells.get(9).getCellType().equals(CellType.STRING)){
                listAuxCodeTeachers.add(cells.get(9).getStringCellValue().split("-")[0].trim());
            }else{
                responseFile.addLogsType("[Fila: " + (cells.get(9).getRowIndex() + 1) + " Columna: J] Solo debes" +
                        " seleccionar el docente, evita escribir en la celda");
            }
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

}
