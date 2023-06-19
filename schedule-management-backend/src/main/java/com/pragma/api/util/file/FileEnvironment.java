package com.pragma.api.util.file;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.util.file.templateclasses.FileRowEnvironment;
import net.bytebuddy.asm.Advice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
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
        List<FileRowEnvironment> fileRows = new ArrayList<>();

        boolean cellEmpty = false;

        InputStream fileExcel = file.getInputStream();


        XSSFWorkbook book = new XSSFWorkbook(fileExcel);


        XSSFSheet sheet = book.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        System.out.println(rowNum);

        for (int i = 1; i <= rowNum; i++) {
            List<Cell> cells = new ArrayList<>();
            System.out.println("Columna: " + i);

            Row row = sheet.getRow(i);

            int columnNum = row.getLastCellNum();

            for (int j = 0; j < columnNum; j++) {
                cells.add(row.getCell(j));
            }


            FileRowEnvironment fileRow = convertCellsToFileRow(cells, responseFile);

            if (fileRow == null) {
                cellEmpty = true;
                break;
            }

            fileRows.add(fileRow);

        }

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


}
