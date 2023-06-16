package com.pragma.api.domain;

public class ResponseExcel {
    private byte[] dataFile;
    private int status;
    private boolean isModified;
    private String message;
    private String nameFile;

    public ResponseExcel() {
    }

    public ResponseExcel(byte[] dataFile, int status, boolean isModified, String message, String nameFile) {
        this.dataFile = dataFile;
        this.status = status;
        this.isModified = isModified;
        this.message = message;
        this.nameFile = nameFile;
    }
    // Getters y setters

    public byte[] getDataFile() {
        return dataFile;
    }

    public void setDataFile(byte[] dataFile) {
        this.dataFile = dataFile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
