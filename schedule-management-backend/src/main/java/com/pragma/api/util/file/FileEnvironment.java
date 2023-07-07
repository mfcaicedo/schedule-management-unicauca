package com.pragma.api.util.file;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import net.bytebuddy.asm.Advice;
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

public class FileEnvironment extends ProcessFile<FileRowEnvironment> {
    @Override
    public List<FileRowEnvironment> getLogs(MultipartFile file, ResponseFile responseFile) throws IOException {
        // Crear una lista vacía de FileRowEnvironment
        List<FileRowEnvironment> fileRows = new ArrayList<>();

        // Inicializar una variable booleana para verificar si una celda está vacía
        boolean cellEmpty = false;

        // Obtener el InputStream del archivo MultipartFile
        InputStream fileExcel = file.getInputStream();

        // Crear un nuevo XSSFWorkbook utilizando el InputStream del archivo
        XSSFWorkbook book = new XSSFWorkbook(fileExcel);

        // Obtener la hoja de cálculo en la posición 0 (primera hoja) del libro
        XSSFSheet sheet = book.getSheetAt(0);

        // Obtener el número de la última fila en la hoja de cálculo
        int rowNum = sheet.getLastRowNum();

        // Imprimir el número de filas
        //System.out.println(rowNum);

        //Establezco el número de filas a recorrer.
        int rowNumOriginal = getRowNumOriginal(rowNum, sheet);
        System.out.println("filas a recorrer: " + rowNumOriginal);

        // Iterar a través de las filas, comenzando desde la fila 1 (omitiendo la primera fila de encabezado)
        for (int i = 1; i <= rowNumOriginal; i++) {
            // Crear una lista vacía de celdas para almacenar las celdas de una fila
            List<Cell> cells = new ArrayList<>();
            // Imprimir el número de columna actual
            //System.out.println("Columna: " + i);
            // Obtener la fila actual en base al índice de fila
            Row row = sheet.getRow(i);
            // Obtener el número de la última celda en la fila
            int columnNum = row.getLastCellNum();
            // Iterar a través de las celdas en la fila
            for (int j = 0; j < columnNum; j++) {
                // Agregar cada celda a la lista de celdas
                cells.add(row.getCell(j));
            }
            // Convertir las celdas en un objeto FileRowEnvironment utilizando el método convertCellsToFileRow
            FileRowEnvironment fileRow = convertCellsToFileRow(cells, responseFile);
            // Agregar el objeto FileRowEnvironment a la lista de filas de archivo
            fileRows.add(fileRow);
        }

        // Devolver la lista de filas de archivo
        return fileRows;
    }

    @Override
    public FileRowEnvironment convertCellsToFileRow(List<Cell> cells, ResponseFile responseFile) {

        FileRowEnvironment fileRow = new FileRowEnvironment(-1,"","",-1,"","","",new ArrayList<>());


        if (cells.size() > 0 && cells.get(0) != null) {
            if (cells.get(0).getCellType().equals(CellType.STRING)) {
                String name = cells.get(0).getStringCellValue().trim();
                if (name.length() > 0) {
                    fileRow.setRowNum(cells.get(0).getRowIndex());
                    fileRow.setName(name.toUpperCase());
                }
            }else if (cells.get(0).getCellType().equals(CellType.NUMERIC)) {
                System.out.println("---------------------Nombre 1:"+cells.get(0));
                int aux = (int) cells.get(0).getNumericCellValue();
                String name = ""+aux;
                if (name.length() > 0) {
                    fileRow.setRowNum(cells.get(0).getRowIndex());
                    fileRow.setName(name.toUpperCase());
                }
            } else if (!cells.get(0).getCellType().equals("")) {
                System.out.println("---------------------Nombre 2:"+cells.get(0));
                String name = ""+(cells.get(0));
                if (name.length() > 0) {
                    fileRow.setRowNum(cells.get(0).getRowIndex());
                    fileRow.setName(name.toUpperCase());
                }
            }

        }

        if (cells.size() > 1 && cells.get(1) != null) {
            if (cells.get(1).getCellType().equals(CellType.STRING)) {
                String location = cells.get(1).getStringCellValue().trim();
                if (location.length() > 0) {
                    fileRow.setRowNum(cells.get(1).getRowIndex());
                    fileRow.setLocation(location.toUpperCase());
                }
            }
        }


        if (cells.size() > 2 && cells.get(2) != null) {
            if (cells.get(2).getCellType().equals(CellType.NUMERIC)) {
                fileRow.setRowNum(cells.get(2).getRowIndex());
                fileRow.setCapacity((int) cells.get(2).getNumericCellValue());
            } else {
                fileRow.setCapacity(-2);
            }
        }


        if (cells.size() > 3 && cells.get(3) != null) {
            if (cells.get(3).getCellType().equals(CellType.STRING)) {
                String environmentType = cells.get(3).getStringCellValue().trim();
                if (environmentType.length() > 0) {
                    fileRow.setRowNum(cells.get(3).getRowIndex());
                    fileRow.setEnvironmentType(environmentType.toUpperCase());
                }
            }
        }

        if (cells.size() > 4 && cells.get(4) != null) {
            if (cells.get(4).getCellType().equals(CellType.STRING)) {
                String faculty = cells.get(4).getStringCellValue().trim();
                if (faculty.length() > 0) {
                    fileRow.setRowNum(cells.get(4).getRowIndex());
                    fileRow.setFaculty(faculty.toUpperCase());
                }
            }
        }


        if (cells.size() > 5 && cells.get(5) != null) {
            if (cells.get(5).getCellType().equals(CellType.STRING)) {
                String availableResources = cells.get(5).getStringCellValue().trim();
                if (availableResources.length() > 0) {
                    fileRow.setRowNum(cells.get(5).getRowIndex());
                    fileRow.setAvailableResources(availableResources.toUpperCase());
                }
            }
        }

            if(cells.size() > 6 && cells.get(6) != null){
                //String Cant = cells.get(6).toString();
                if(cells.get(6).getCellType() == CellType.NUMERIC){
                    int onequantity = Double.valueOf(cells.get(6).toString()).intValue();
                    List<Integer> listaint = new ArrayList<>();
                    listaint.add(onequantity);
                    //for one
                    fileRow.setQuantity(listaint);

                }else if(!(cells.get(6)==null) && !(cells.get(6).getStringCellValue().equals("no aplica"))) {
                    try {
                        String lista = cells.get(6).getStringCellValue();
                        String[] lista2 = lista.split(",");
                        List<Integer> listaint = new ArrayList<>();
                        //para varios

                        for (int i = 0; i < lista2.length; i++) {
                            listaint.add(Integer.parseInt(lista2[i]));

                        }
                        fileRow.setQuantity(listaint);
                    }
                    catch (Exception e){
                        System.out.println("-------------------ENTRA AAQIO");
                        fileRow.setQuantity(null);
                    }
                }else if(cells.get(6).getStringCellValue().equals("no aplica")){
                    fileRow.setQuantity(new ArrayList<>());
                }

            }else{
                fileRow.setQuantity(null);
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
