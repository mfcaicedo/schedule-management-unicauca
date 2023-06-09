package com.pragma.api.services;

import com.pragma.api.model.*;
import com.pragma.api.domain.ResponseFile;
import com.pragma.api.domain.enumStatusFile;
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
        List<String> infoErrores = new ArrayList<>();
        List<String> infoErroresVacias = new ArrayList<>();
        List<String> infoErroresTipos = new ArrayList<>();
        List<String> infoSuccess = new ArrayList<>();
        //----------------------------------------------------------------------
        List<String> subjectCodes = new ArrayList<>();
        List<String> subjectNames = new ArrayList<>();
        List<FileRowSubject> archivoMaterias = new ArrayList<>();
        //----------------------------------------------------------------------
        int contRows = 0;
        int contSuccess = 0;
        int contError = 0;
        for (FileRowSubject log : logs) {
            int rowNum = log.getRowNum();
            if (rowNum != -1) {
                contRows++;
                rowNum = rowNum + 1;
                boolean errorSubject = false;
                boolean errorProgram = false;
                boolean errorVacias = false;
                boolean errorTipos = false;
                boolean errorRepetidos = false;

//                System.out.println("CODIGO MATERIA: " + log.getSubjectCode());
                if (log.getSubjectCode().trim().length() == 0) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL CODIGO DE LA MATERIA ESTA VACIO (CODIGO OBLIGATORIO)");
                    errorVacias = true;
                } else {
                    if (this.existsInList(subjectCodes, log.getSubjectCode())) {
                        infoErrores.add("[FILA " + rowNum + "]  EL CODIGO DE LA MATERIA ESTA REPETIDO: " + log.getSubjectCode());
                        errorRepetidos = true;
                    } else {
                        subjectCodes.add(log.getSubjectCode());
                        if (this.subjectRepository.existsById(log.getSubjectCode())) {
                            infoErrores.add("[FILA " + rowNum + "]  EL CODIGO DE LA MATERIA YA EXISTE: " + log.getSubjectCode());
                            errorSubject = true;
                        }
                    }
                }

                //--------------------------------------------------------------
                if (log.getName().trim().length() == 0) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL NOMBRE DE LA MATERIA ESTA VACIO (NOMBRE MATERIA OBLIGATORIO)");
                    errorVacias = true;
                } else {
                    if (this.existsInList(subjectNames, log.getName())) {
                        infoErrores.add("[FILA " + rowNum + "]  EL NOMBRE DE LA MATERIA ESTA REPETIDO: " + log.getName());
                        errorRepetidos = true;
                    } else {
                        subjectNames.add(log.getName());
                        if (this.subjectRepository.existsByNameAndProgram_ProgramId(log.getName(), log.getProgramCode())) {
                            infoErrores.add("[FILA " + rowNum + "]  EL NOMBRE DE LA MATERIA YA EXISTE EN EL PROGRAMA (" + log.getProgramCode() + "): " + log.getName());
                            errorRepetidos = true;
                        }
                    }
                }
                //--------------------------------------------------------------
                if (log.getSemester() == -1) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL SEMESTRE ESTA VACIO (SEMESTRE OBLIGATORIO)");
                    errorVacias = true;
                }
                if (log.getSemester() == -2) {
                    infoErroresTipos.add("[FILA " + rowNum + "]  EL SEMESTRE NO ES NUMERICO");
                    errorTipos = true;
                }
                //------------------------------------------------------------------
                if (log.getInBlock().trim().length() == 0) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL CAMPO EN BLOQUE ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                }
                //------------------------------------------------------------------
                if (log.getWeeklyOverload() == -1) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL CAMPO HORAS SEMANALES ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                }
                if (log.getWeeklyOverload() == -2) {
                    infoErroresTipos.add("[FILA " + rowNum + "]  LAS HORAS SEMANALES NO ES NUMERICO");
                    errorTipos = true;
                }
                //--------------------------------------------------------------
//                System.out.println("PROGRAMA: " + log.getProgramCode());
                if (log.getProgramCode().trim().length() == 0) {
                    infoErroresVacias.add("[FILA " + rowNum + "]  EL CODIGO DEL PROGRAMA ESTA VACIO (CAMPO OBLIGATORIO)");
                    errorVacias = true;
                } else {
                    if (!this.programRepository.existsById(log.getProgramCode())) {
                        infoErrores.add("[FILA " + rowNum + "]  EL CODIGO DEL PROGRAMA NO SE ENCUENTRA REGISTRADO: " + log.getProgramCode());
                        errorProgram = true;
                    }
                }
                //--------------------------------------------------------------
                if (!errorSubject && !errorProgram && !errorVacias && !errorTipos && !errorRepetidos) {
                    infoSuccess.add("[FILA " + rowNum + "]  LISTA PARA SER REGISTRADA");
                    archivoMaterias.add(log);
                    contSuccess++;
                } else {
                    contError++;
                }
            }
        }
        enumStatusFile statusFile = enumStatusFile.NO_PROCESS;
        int contSaveRows = 0;
        if (contRows > 0) {
            if (contError > 0) {
                statusFile = enumStatusFile.ERROR;
            } else {
                statusFile = enumStatusFile.SUCCESS;
                if (!archivoMaterias.isEmpty()) {
                    contSaveRows = this.saveFile(archivoMaterias);
                    if (contSaveRows > 0) {
                        statusFile = enumStatusFile.SAVED;
                    } else {
                        statusFile = enumStatusFile.NO_SAVED;
                    }
                }
            }
        }
        responseFile.setStatusFile(statusFile);
        responseFile.setContRows(contRows);
        responseFile.setContErrorRows(contError);
        responseFile.setContSuccessRows(contSuccess);
        responseFile.setContSaveRows(contSaveRows);
        responseFile.setLogsType(infoErroresTipos);
        responseFile.setLogsEmptyFields(infoErroresVacias);
        responseFile.setLogsGeneric(infoErrores);
        responseFile.setLogsSuccess(infoSuccess);
        return responseFile;
    }

    private int saveFile(List<FileRowSubject> archivoMaterias) {
        List<Subject> materias = new ArrayList<>();
        for (FileRowSubject materia : archivoMaterias) {
            Boolean inBlock = false;
            if (materia.getInBlock().equals("SI")) {
                inBlock = true;
            }
            Program objPrograma = this.programRepository.findByProgramId(materia.getProgramCode());
            Subject objMateria = new Subject(materia.getSubjectCode(), materia.getName(), materia.getWeeklyOverload(), inBlock, materia.getSemester(), objPrograma, null);
            materias.add(objMateria);
        }
        return this.subjectRepository.saveAll(materias).size();
    }

    private boolean existsInList(List<String> lista, String buscado) {
        boolean encontrado = false;
        for (String elemento : lista) {
            if (elemento.equals(buscado)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

}
