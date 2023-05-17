package com.pragma.api.domain;

import java.util.LinkedList;
import java.util.List;

public class ResponseFile {

    private int status;
    private String message;
    private String TypeError;
    //ERRORES DE TIPO
    private List<String> logsType;
    //ERRORES DE CAMPOS
    private List<String> logsEmptyFields;
    //ERRORES GENERALES DE LA CLASE ESPECIFICA
    private List<String> logsGeneric;

    public ResponseFile() {
        this.logsType = new LinkedList();
        this.logsEmptyFields = new LinkedList();
        this.logsGeneric = new LinkedList();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeError() {
        return TypeError;
    }

    public void setTypeError(String TypeError) {
        this.TypeError = TypeError;
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

}
