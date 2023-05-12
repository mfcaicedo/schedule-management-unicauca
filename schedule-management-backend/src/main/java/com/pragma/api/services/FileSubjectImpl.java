package com.pragma.api.services;

import com.pragma.api.model.*;
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
    public List<String> uploadFile(MultipartFile file) throws IOException {
        FileSubject fileSubject = new FileSubject();
        List<FileRowSubject> logs = fileSubject.getLogs(file);
        return processFile(logs);
    }

    @Override
    public List<String> processFile(List<FileRowSubject> logs) {
        List<String> infoLogs = new ArrayList<>();
        List<String> infoErrores = new ArrayList<>();
        int contOk = 0;
        int contError = 0;
        for (FileRowSubject log : logs) {

//            Program program = null;
            int rowNum = log.getRowNum() + 1;
            infoErrores.clear();

//            System.out.println("CODIGO MATERIA: " + log.getSubjectCode());

            Boolean errorSubject = false;
            if (subjectRepository.existsById(log.getSubjectCode())) {
                infoErrores.add(" - EL CODIGO DE LA MATERIA YA EXISTE: " + log.getSubjectCode());
                errorSubject = true;
            }

            //------------------------------------------------------------------
//            System.out.println("PROGRAMA: " + log.getProgramWhitCode());
            String programWhitCode = log.getProgramWhitCode();
            String[] partesPrograma = programWhitCode.split("-");
            String programID = partesPrograma[0];
            String programName = partesPrograma[1];

            Boolean errorProgram = false;
            if (!programRepository.existsById(programID)) {
                infoErrores.add(" - EL CODIGO DEL PROGRAMA NO SE ENCUENTRA REGISTRADO: " + programWhitCode);
                errorProgram = true;
            }

            if (!errorSubject && !errorProgram) {
                infoLogs.add("[FILA " + rowNum + "] LISTA PARA SER REGISTRADA");
                contOk++;
            } else {
                infoLogs.add("[FILA " + rowNum + "] CONTIENE ERRORES:");
                for (String infoError : infoErrores) {
                    infoLogs.add(infoError);
                }
                contError++;
            }
        }
        if (contError > 0) {
            infoLogs.add(0, "ESTADO ARCHIVO: Error");
        } else {
            infoLogs.add(0, "ESTADO ARCHIVO: Success");
        }
        infoLogs.add(1, "FILAS CON ERRORES: " + contError);
        infoLogs.add(2, "FILAS CORRECTAS: " + contOk);
        return infoLogs;
    }

}
