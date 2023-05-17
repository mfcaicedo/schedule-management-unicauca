package com.pragma.api.services;

import com.pragma.api.model.*;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.repository.ISubjectRepository;
import com.pragma.api.repository.IProgramRepository;
import com.pragma.api.util.file.FileSubject;
import com.pragma.api.util.file.templateclasses.FileRowSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileSubjectImpl implements IFileSubjectService {

    /* Se utiliza para verificar que el programa al que pertenece la materia ya 
       este registrado en la BD
     */
    private ISubjectRepository subjectRepository;
    private IProgramRepository programRepository;

    @Autowired
    public FileSubjectImpl(ISubjectRepository subjectRepository, IProgramRepository programRepository) {
        this.subjectRepository = subjectRepository;
        this.programRepository = programRepository;
    }

    @Override
    public ResponseFile uploadFile(MultipartFile file) throws IOException {
        FileSubject fileSubject = new FileSubject();
        List<FileRowSubject> logs = fileSubject.getLogs(file);
        return processFile(logs);
    }

    @Override
    public ResponseFile processFile(List<FileRowSubject> logs) {
        ResponseFile responseFile = new ResponseFile();
        List<String> infoLogs = new ArrayList<>();
        List<String> infoErrores = new ArrayList<>();
        List<String> infoLogsVacias = new ArrayList<>();
        List<String> infoErroresVacias = new ArrayList<>();
        int contOk = 0;
        int contError = 0;
        for (FileRowSubject log : logs) {

//            Program program = null;
            int rowNum = log.getRowNum();
            if (rowNum != -1) {
                rowNum = rowNum + 1;
                infoErrores.clear();
                infoErroresVacias.clear();
                Boolean errorSubject = false;
                Boolean errorProgram = false;
                Boolean errorVacias = false;
                System.out.println("CODIGO MATERIA: " + log.getSubjectCode());
                if (log.getSubjectCode().trim().length() == 0) {
                    infoErroresVacias.add(" - EL CODIGO DE LA MATERIA ESTA VACIO (CODIGO OBLIGATORIO)");
                    errorVacias = true;
                } else {
                    if (subjectRepository.existsById(log.getSubjectCode())) {
                        infoErrores.add(" - EL CODIGO DE LA MATERIA YA EXISTE: " + log.getSubjectCode());
                        errorSubject = true;
                    }
                }
                //------------------------------------------------------------------
                if (log.getName().trim().length() == 0) {
                    infoErroresVacias.add(" - EL NOMBRE DE LA MATERIA ESTA VACIO (NOMBRE MATERIA OBLIGATORIO)");
                    errorVacias = true;
                }
                //------------------------------------------------------------------
                if (log.getSemester() == -1) {
                    infoErroresVacias.add(" - EL SEMESTRE ESTA VACIO (SEMESTRE OBLIGATORIO)");
                    errorVacias = true;
                }
                //------------------------------------------------------------------
                if (log.getInBlock().trim().length() == 0) {
                    infoErroresVacias.add(" - EL CAMPO EN BLOQUE ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                }
                //------------------------------------------------------------------
                if (log.getWeeklyOverload() == -1) {
                    infoErroresVacias.add(" - EL CAMPO HORAS SEMANALES ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                }
                //------------------------------------------------------------------
                System.out.println("PROGRAMA: " + log.getProgramWhitCode());
                String programWhitCode = log.getProgramWhitCode();
                if (programWhitCode.trim().length() == 0) {
                    infoErroresVacias.add(" - EL CODIGO DEL PROGRAMA ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                } else {
                    String[] partesPrograma = programWhitCode.split("-");
                    String programID = partesPrograma[0];
//                    String programName = partesPrograma[1];
                    if (!programRepository.existsById(programID)) {
                        infoErrores.add(" - EL CODIGO DEL PROGRAMA NO SE ENCUENTRA REGISTRADO: " + programWhitCode);
                        errorProgram = true;
                    }
                }

                if (!errorSubject && !errorProgram && !errorVacias) {
                    infoLogs.add("[FILA " + rowNum + "] LISTA PARA SER REGISTRADA");
                    contOk++;
                } else {
                    if (infoErroresVacias.size() > 0) {
                        infoLogsVacias.add("[FILA " + rowNum + "] CONTIENE ERRORES:");
                        for (String infoErrorVacia : infoErroresVacias) {
                            infoLogsVacias.add(infoErrorVacia);
                        }
                    }
                    if (infoErrores.size() > 0) {
                        infoLogs.add("[FILA " + rowNum + "] CONTIENE ERRORES:");
                        for (String infoError : infoErrores) {
                            infoLogs.add(infoError);
                        }
                    }
                    contError++;
                }
            }
        }
        if (contError > 0) {
            infoLogs.add(0, "ESTADO ARCHIVO: Error");
        } else {
            infoLogs.add(0, "ESTADO ARCHIVO: Success");
        }
        infoLogs.add(1, "FILAS CON ERRORES: " + contError);
        infoLogs.add(2, "FILAS CORRECTAS: " + contOk);

        responseFile.setStatus(200);
        responseFile.setLogsGeneric(infoLogs);
        responseFile.setLogsEmptyFields(infoLogsVacias);
        return responseFile;
    }

}
