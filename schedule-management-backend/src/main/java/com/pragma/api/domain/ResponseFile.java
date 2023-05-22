package com.pragma.api.domain;

import java.util.LinkedList;
import java.util.List;

public class ResponseFile {

    private enumStatusFile statusFile;
    private int contRows;
    private int contErrorRows;
    private int contSuccessRows;
    private int contSaveRows;

    //ERRORES DE TIPO
    private List<String> logsType;

    //ERRORES DE CAMPOS
    private List<String> logsEmptyFields;

    //ERRORES GENERALES DE LA CLASE ESPECIFICA
    private List<String> logsGeneric;

    //ERRORES GENERALES DE LA CLASE ESPECIFICA
    private List<String> logsSuccess;

    public ResponseFile() {
        this.statusFile = enumStatusFile.NO_PROCESS;
        this.contRows = 0;
        this.contErrorRows = 0;
        this.contSuccessRows = 0;
        this.contSaveRows = 0;
        this.logsType = new LinkedList();
        this.logsEmptyFields = new LinkedList();
        this.logsGeneric = new LinkedList();
        this.logsSuccess = new LinkedList();
    }

    public enumStatusFile getStatusFile() {
        return statusFile;
    }

    public void setStatusFile(enumStatusFile statusFile) {
        this.statusFile = statusFile;
    }

    public int getContRows() {
        return contRows;
    }

    public void setContRows(int contRows) {
        this.contRows = contRows;
    }

    public int getContErrorRows() {
        return contErrorRows;
    }

    public void setContErrorRows(int contErrorRows) {
        this.contErrorRows = contErrorRows;
    }

    public int getContSuccessRows() {
        return contSuccessRows;
    }

    public void setContSuccessRows(int contSuccessRows) {
        this.contSuccessRows = contSuccessRows;
    }

    public int getContSaveRows() {
        return contSaveRows;
    }

    public void setContSaveRows(int contSaveRows) {
        this.contSaveRows = contSaveRows;
    }

    public List<String> getLogsType() {
        return logsType;
    }

    public void setLogsType(List<String> logsType) {
        this.logsType = logsType;
    }

    public List<String> getLogsEmptyFields() {
        return logsEmptyFields;
    }

    public void setLogsEmptyFields(List<String> logsEmptyFields) {
        this.logsEmptyFields = logsEmptyFields;
    }

    public List<String> getLogsGeneric() {
        return logsGeneric;
    }

    public void setLogsGeneric(List<String> logsGeneric) {
        this.logsGeneric = logsGeneric;
    }

    public List<String> getLogsSuccess() {
        return logsSuccess;
    }

    public void setLogsSuccess(List<String> logsSuccess) {
        this.logsSuccess = logsSuccess;
    }

}
